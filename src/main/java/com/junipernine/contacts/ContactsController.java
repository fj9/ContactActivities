package com.junipernine.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */

@RestController
@RequestMapping("/contacts")
public class ContactsController {
    private final ContactRepository contactRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    ContactsController(ContactRepository contactRepository, ActivityRepository activityRepository) {
        this.contactRepository = contactRepository;
        this.activityRepository = activityRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> list() {
        return contactRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}")
    public Contact view(@PathVariable Long id) {
        validatContact(id.toString());
        return contactRepository.findOne(id);
    }
    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}/activities")
    public Collection<Activity> viewActivities(@PathVariable Long id) {
        validatContact(id.toString());
        return activityRepository.findByContactId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Contact newContact) {
        Contact result = contactRepository.save(new Contact(newContact.getFirstName(), newContact.getLastName(), newContact.getEmailAddress()));
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().buildAndExpand(result.getId()).toUri()).build();

    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/{contactId}/activity")
    public ResponseEntity<?> addActivity(@PathVariable String contactId, @RequestBody Activity newActivity) {
        validatContact(contactId);
        Contact contact = contactRepository.findOne(Long.parseLong(contactId));
        Activity result = activityRepository.save(new Activity(contact, newActivity.getTitle(), newActivity.getNotes(), newActivity.getSubject()));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri()).build();
    }
//    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<?> edit(@PathVariable String contactId, @RequestBody Contact changedContact) {
//       validatContact(contactId);
//        contactRepository.s
//    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@PathVariable String contactId) {
        validatContact(contactId);
        contactRepository.delete(Long.parseLong(contactId));
        return ResponseEntity.accepted().build();
    }

    private void validatContact(String contactId) {
        contactRepository.findById(Long.parseLong(contactId)).orElseThrow(() -> new ContactNotFoundException(contactId));
    }
}

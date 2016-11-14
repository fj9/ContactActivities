package com.junipernine.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
@RestController
@RequestMapping("/activities")
public class ActivityController {



    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Activity> list() {
        return activityRepository.findAll();
    }
    @RequestMapping(method = RequestMethod.GET, value="/contact/{id}")
    public Collection<Activity> listByContact(@PathVariable("id") Long id) {
        return activityRepository.findByContactId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Activity newActivity) {
        Activity result = activityRepository.save(newActivity);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().buildAndExpand(result.getId()).toUri()).build();

    }

//    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<?> edit(@PathVariable String contactId, @RequestBody Contact changedContact) {
//       validatContact(contactId);
//        contactRepository.s
//    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@PathVariable String contactId) {
        validatActivity(contactId);
        activityRepository.delete(Long.parseLong(contactId));
        return ResponseEntity.accepted().build();
    }

    private void validatActivity(String activityId) {
        activityRepository.findById(Long.parseLong(activityId)).orElseThrow(()-> new ActivityNotFoundException(activityId));
    }
}
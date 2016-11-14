package com.junipernine.contacts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
@Entity
public class Activity {

    private String title;
    private String notes;
    private String subject;

    @JsonIgnore
    @ManyToOne
    private Contact contact;

    @Id
    @GeneratedValue
    private Long id;

    public Activity(Contact contact, String title, String notes, String subject) {
        this.contact=contact;
        this.title = title;
        this.notes = notes;
        this.subject = subject;
    }

    Activity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public Contact getContact() {
        return contact;
    }
}

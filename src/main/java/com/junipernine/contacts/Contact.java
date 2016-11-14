package com.junipernine.contacts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    //    private String telephoneNumber;
    @OneToMany(mappedBy = "contact")
    private Set<Activity> activities = new HashSet<>();


    public Long getId() {
        return id;
    }

    public Contact(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    Contact() {
    }
}

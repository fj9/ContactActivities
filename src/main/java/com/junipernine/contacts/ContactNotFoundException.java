package com.junipernine.contacts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String contactId) {
        super("Could not find contact for id = " + contactId);
    }
}

package com.junipernine.contacts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by freya.juniper-nine on 13/11/2016.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(String activityId) {

                super("Could not find contact for id = " + activityId);
    }
}

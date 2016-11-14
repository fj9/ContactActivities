package com.junipernine.contacts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

//    @Bean
//    CommandLineRunner init(ContactRepository contactRepository , ActivityRepository activityRepository){
//        return (evt) -> Arrays.asList("freya", "tony").forEach(
//               c ->{
//                   Contact contact = contactRepository.save(new Contact(c));
//                   activityRepository.save(new Activity( contact, "Dance", "Fun", "Exercise"));
//
//               }
//        );
//
//    }
}

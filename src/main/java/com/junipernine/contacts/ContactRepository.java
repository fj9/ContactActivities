package com.junipernine.contacts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Collection<Contact> findByFirstName (String firstName);

    List<Contact> findAll();

    Optional<Contact> findById(long l);
}


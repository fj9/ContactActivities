package com.junipernine.contacts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Collection<Activity> findBySubject(String subject);

    Optional<Activity> findById(Long l);

    Collection<Activity> findByContactId(Long contactId);
}

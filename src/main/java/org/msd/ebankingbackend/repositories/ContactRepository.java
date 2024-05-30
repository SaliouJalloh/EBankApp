package org.msd.ebankingbackend.repositories;

import org.msd.ebankingbackend.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByCustomerId(Long userId);
}
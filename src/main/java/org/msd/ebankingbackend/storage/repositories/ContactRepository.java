package org.msd.ebankingbackend.storage.repositories;

import org.msd.ebankingbackend.storage.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

}

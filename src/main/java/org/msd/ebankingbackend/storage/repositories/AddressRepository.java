package org.msd.ebankingbackend.storage.repositories;

import org.msd.ebankingbackend.storage.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}

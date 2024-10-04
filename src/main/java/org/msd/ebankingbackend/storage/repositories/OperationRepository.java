package org.msd.ebankingbackend.storage.repositories;

import org.msd.ebankingbackend.storage.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

}

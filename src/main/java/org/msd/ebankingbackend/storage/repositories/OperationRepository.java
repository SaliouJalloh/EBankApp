package org.msd.ebankingbackend.storage.repositories;

import org.msd.ebankingbackend.storage.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

}

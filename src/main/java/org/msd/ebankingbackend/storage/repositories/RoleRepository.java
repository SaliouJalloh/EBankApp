package org.msd.ebankingbackend.storage.repositories;

import org.msd.ebankingbackend.storage.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String roleCustomer);
}

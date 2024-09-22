package org.msd.ebankingbackend.storage.repositories;


import org.msd.ebankingbackend.storage.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}

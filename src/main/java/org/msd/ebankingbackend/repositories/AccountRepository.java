package org.msd.ebankingbackend.repositories;


import org.msd.ebankingbackend.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByIban(String iban);
    //Optional<Account> findByUserId(Integer id);
}

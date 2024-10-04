package org.msd.ebankingbackend.services;

import org.msd.ebankingbackend.storage.models.Account;

import java.util.List;

public interface IAccountService {

    Account saveAccount(Account account);

    List<Account> findAllAccounts();
    
}

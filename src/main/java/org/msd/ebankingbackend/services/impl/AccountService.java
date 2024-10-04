package org.msd.ebankingbackend.services.impl;

import org.msd.ebankingbackend.services.IAccountService;
import org.msd.ebankingbackend.storage.models.Account;

import java.util.List;

public class AccountService implements IAccountService {
    @Override
    public Account saveAccount(Account account) {
        return null;
    }

    @Override
    public List<Account> findAllAccounts() {
        return List.of();
    }
}

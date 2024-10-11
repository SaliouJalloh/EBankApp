package org.msd.ebankingbackend.service.impl;

import org.msd.ebankingbackend.service.IAccountService;
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

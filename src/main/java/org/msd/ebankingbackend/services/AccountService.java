package org.msd.ebankingbackend.services;

import java.util.List;

import org.msd.ebankingbackend.dtos.AccountDto;
import org.msd.ebankingbackend.dtos.AccountHistoryDto;
import org.msd.ebankingbackend.dtos.OperationDto;
import org.msd.ebankingbackend.exception.AccountNotFoundException;
import org.msd.ebankingbackend.exception.BalanceNotSufficientException;
import org.msd.ebankingbackend.exception.CustomerNotFoundException;

public interface AccountService extends AbstractService<AccountDto> {

    AccountDto updateAccount(AccountDto accountDto);

    void saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

    void saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    void debit(Long accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void credit(Long accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void transfer(Long accountIdSource, Long accountIdDestination, double amount) throws BalanceNotSufficientException;

    List<OperationDto> accountHistory(Long accountId);

    AccountHistoryDto getAccountHistory(Long accountId, int page, int size) throws AccountNotFoundException;
}

package org.msd.ebankingbackend.services;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.msd.ebankingbackend.dtos.AccountDto;
import org.msd.ebankingbackend.dtos.AccountHistoryDto;
import org.msd.ebankingbackend.dtos.OperationDto;
import org.msd.ebankingbackend.exception.BalanceNotSufficientException;

public interface AccountService extends AbstractService<AccountDto> {

    AccountDto updateAccount(AccountDto accountDto);

    void saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws EntityNotFoundException;

    void saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws EntityNotFoundException;

    void debit(Long accountId, double amount, String description) throws EntityNotFoundException, BalanceNotSufficientException;
    void credit(Long accountId, double amount, String description) throws EntityNotFoundException, BalanceNotSufficientException;
    void transfer(Long accountIdSource, Long accountIdDestination, double amount) throws BalanceNotSufficientException;

    List<OperationDto> accountHistory(Long accountId);

    AccountHistoryDto getAccountHistory(Long accountId, int page, int size) throws EntityNotFoundException;
}

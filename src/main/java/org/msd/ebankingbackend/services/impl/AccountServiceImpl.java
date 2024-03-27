package org.msd.ebankingbackend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.dtos.AccountDto;
import org.msd.ebankingbackend.dtos.AccountHistoryDto;
import org.msd.ebankingbackend.dtos.OperationDto;
import org.msd.ebankingbackend.entities.*;
import org.msd.ebankingbackend.enums.AccountStatus;
import org.msd.ebankingbackend.enums.OperationType;
import org.msd.ebankingbackend.exception.AccountNotFoundException;
import org.msd.ebankingbackend.exception.BalanceNotSufficientException;
import org.msd.ebankingbackend.exception.CustomerNotFoundException;
import org.msd.ebankingbackend.mappers.AccountMapper;
import org.msd.ebankingbackend.mappers.OperationMapper;
import org.msd.ebankingbackend.repositories.AccountRepository;
import org.msd.ebankingbackend.repositories.CustomerRepository;
import org.msd.ebankingbackend.repositories.OperationRepository;
import org.msd.ebankingbackend.services.AccountService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	private final OperationRepository operationRepository;
	private final AccountMapper accountMapper;
    private final EntityValidatorService<AccountDto> validator;

	@Override
    public List<AccountDto> findAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> {
            if (account instanceof SavingAccount savingAccount) {
                return accountMapper.fromSavingAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) account;
                return accountMapper.fromCurrentAccount(currentAccount);
            }
        }).collect(Collectors.toList());
    }
	@Override
	public AccountDto findAccountById(Long accountId) throws AccountNotFoundException {
		log.info("return a account");
		Account account = accountRepository.findById(accountId)
				.orElseThrow(()-> new AccountNotFoundException("BankAccount not found"));
		if(account instanceof SavingAccount savingAccount){
            return accountMapper.fromSavingAccount(savingAccount);
		} else {
			CurrentAccount currentAccount = (CurrentAccount) account;
			return accountMapper.fromCurrentAccount(currentAccount);
		}
	}

	private static void initializeAccount(double initialBalance, Account account, Customer customer) throws CustomerNotFoundException {
		if(customer == null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		account.setCreatedAt(LocalDateTime.now());
		account.setBalance(initialBalance);
		account.setCustomer(customer);
		account.setCurrency("USD");
		account.setStatus(AccountStatus.CREATED);
	}

	@Override
	public void saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
		log.info("Current_account saved");
		Customer customer = customerRepository.findById(customerId).orElse(null);
		CurrentAccount currentAccount = new CurrentAccount();
		initializeAccount(initialBalance, currentAccount, customer);
		currentAccount.setOverDraft(overDraft);
		CurrentAccount savedCurrentAccount = accountRepository.save(currentAccount);
		accountMapper.fromCurrentAccount(savedCurrentAccount);
	}

	@Override
	public void saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
		log.info("Saving_account saved");
		Customer customer = customerRepository.findById(customerId).orElse(null);
		SavingAccount savingAccount = new SavingAccount();
		initializeAccount(initialBalance, savingAccount,customer);
		savingAccount.setInterestRate(interestRate);
		SavingAccount savedAccount = accountRepository.save(savingAccount);
		accountMapper.fromSavingAccount(savedAccount);
	}

	private static Operation initializeOperations(OperationType debit, double amount, String description, Account account) {
		Operation operation = new Operation();
		operation.setCreatedAt(LocalDateTime.now());
		operation.setOperationDate(LocalDateTime.now());
		operation.setType(debit);
		operation.setAmount(amount);
		operation.setDescription(description);
		operation.setAccount(account);
		return operation;
	}

	@Override
	public void debit(Long accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException {

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new AccountNotFoundException("BankAccount not found"));

		if(account.getBalance() < amount){
			throw new BalanceNotSufficientException("Balance not sufficient");
		}
		Operation operation = initializeOperations(OperationType.DEBIT, amount, description, account);
		account.setBalance(account.getBalance()-amount);
		accountRepository.save(account);
		operationRepository.save(operation);
	}

	@Override
	public void credit(Long accountId, double amount, String description) throws AccountNotFoundException {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(()->new AccountNotFoundException("BankAccount not found"));

		Operation operation = initializeOperations(OperationType.CREDIT, amount, description, account);
		operationRepository.save(operation);
		account.setBalance(account.getBalance()+amount);
		accountRepository.save(account);
	}

	@Override
	public void transfer(Long accountIdSource, Long accountIdDestination, double amount) throws BalanceNotSufficientException {
		debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
		credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
	}

	@Override
	public List<OperationDto> accountHistory(Long accountId){
		List<Operation> operations = operationRepository.findAccountById(accountId);
		return operations.stream().map(OperationMapper::toOperationDto).collect(Collectors.toList());
	}

	@Override
	public AccountHistoryDto getAccountHistory(Long accountId, int page, int size) throws AccountNotFoundException {
		Account account = accountRepository.findById(accountId).orElse(null);
		if(account == null){
			throw new AccountNotFoundException("Account not Found");
		}
		Page<Operation> operations = operationRepository.findByAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
		AccountHistoryDto accountHistoryDto = new AccountHistoryDto();
		List<OperationDto> operationDtos = operations.getContent().stream().map(OperationMapper::toOperationDto).collect(Collectors.toList());
		accountHistoryDto.setOperationDtos(operationDtos);
		accountHistoryDto.setAccountId(account.getId());
		accountHistoryDto.setBalance(account.getBalance());
		accountHistoryDto.setCurrentPage(page);
		accountHistoryDto.setPageSize(size);
		accountHistoryDto.setTotalPages(operations.getTotalPages());
		return accountHistoryDto;
	}

	//	@Override
	//	public Account saveAccount(double initialBalance, String type, Long customerId) {
	//		log.info("Saved a account");
	//		Customer customer = customerRepository.findById(customerId).orElse(null);
	//        if(customer == null) {
	//        	throw new CustomerNotFoundException("Customer not found");        	
	//        }        
	//        Account account = new CurrentAccount();
	//        
	//        account.setId(UUID.randomUUID().toString());
	//        account.setCreatedAt(new Date());
	//        account.setBalance(initialBalance);
	//        if(account instanceof CurrentAccount) {
	//        	account.setOverDraft(overDraft);        	
	//        }else {
	//        	account.set
	//        }
	//
	//        account.setCustomer(customer);
	//
	//        CurrentAccount savedAccount = accountRepository.save(currentAccount);
	//
	//		accountRepository.save(savedAccount);
	//		return null;
	//	}

}

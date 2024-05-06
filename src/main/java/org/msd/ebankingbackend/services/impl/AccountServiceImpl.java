package org.msd.ebankingbackend.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.msd.ebankingbackend.dtos.*;
import org.msd.ebankingbackend.entities.*;
import org.msd.ebankingbackend.enums.AccountStatus;
import org.msd.ebankingbackend.enums.OperationType;
import org.msd.ebankingbackend.exception.AccountNotFoundException;
import org.msd.ebankingbackend.exception.BalanceNotSufficientException;
import org.msd.ebankingbackend.mappers.AccountMapper;
import org.msd.ebankingbackend.mappers.CustomerMapper;
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
	private final CustomerMapper customerMapper;
    private final EntityValidatorService<AccountDto> validator;

	@Override
	public AccountDto save(AccountDto dto) {
		validator.validateInput(dto);
		if (dto instanceof CurrentAccountDto currentAccountDto) {
			CurrentAccount currentAccount = accountMapper.fromCurrentAccountDto(currentAccountDto);
			CurrentAccount savedAccount = accountRepository.save(currentAccount);
			// generate random IBAN when creating new account else do not update the IBAN
			if (dto.getId() == null) {
				savedAccount.setIban(generateRandomIban());
			}
			return accountMapper.fromCurrentAccount(savedAccount);
		} else {
			SavingAccount savingAccount = accountMapper.fromSavingAccountDto((SavingAccountDto) dto);
			SavingAccount savedAccount = accountRepository.save(savingAccount);
			// generate random IBAN when creating new account else do not update the IBAN
			if (dto.getId() == null) {
				savedAccount.setIban(generateRandomIban());
			}
			return accountMapper.fromSavingAccount(savedAccount);
		}
	}

	@Override
	public List<AccountDto> findAll() {
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
	public AccountDto findById(Long accountId) {
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

	@Override
	public AccountDto updateAccount(AccountDto accountDto) {
		log.info("Account Updated");
		Account account = accountRepository.findById(accountDto.getId())
				.orElseThrow(()-> new AccountNotFoundException("BankAccount not found"));

		if(account instanceof CurrentAccount currentAccount){
			accountMapper.fromCurrentAccount(currentAccount);
			CurrentAccount savedAccount = accountRepository.save(currentAccount);
			return accountMapper.fromCurrentAccount(savedAccount);
		} else {
			SavingAccount savingAccount = (SavingAccount) account;
			accountMapper.fromSavingAccount(savingAccount);
			SavingAccount savedAccount = accountRepository.save(savingAccount);
			return accountMapper.fromSavingAccount(savedAccount);
		}
	}

	@Override
	public void delete(Long id) {
		// todo check delete
		accountRepository.deleteById(id);
	}

	private void initializeAccount(double initialBalance, AccountDto accountDto, CustomerDto customerDto) throws EntityNotFoundException {
		if(customerDto == null) {
			throw new EntityNotFoundException("Customer not found");
		}
		accountDto.setCreatedAt(LocalDateTime.now());
		accountDto.setBalance(initialBalance);
		accountDto.setCustomerDto(customerDto);
		accountDto.setCurrency("EUR");
		accountDto.setStatus(AccountStatus.CREATED);
		// generate random IBAN when creating new account else do not update the IBAN
		if(accountDto.getId() == null) {
			accountDto.setIban(generateRandomIban());
		}
	}

	@Override
	public void saveCurrentAccount(double initialBalance, double overDraft, Long customerId) throws EntityNotFoundException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		CurrentAccountDto currentAccountDto = new CurrentAccountDto();
		initializeAccount(initialBalance, currentAccountDto, customerMapper.fromCustomer(customer));
		currentAccountDto.setOverDraft(overDraft);
		CurrentAccount savedCurrentAccount = accountRepository.save(accountMapper.fromCurrentAccountDto(currentAccountDto));
		accountMapper.fromCurrentAccount(savedCurrentAccount);
		log.info("Current_account saved");
	}

	@Override
	public void saveSavingAccount(double initialBalance, double interestRate, Long customerId) throws EntityNotFoundException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		SavingAccountDto savingAccountDto = new SavingAccountDto();
		initializeAccount(initialBalance, savingAccountDto, customerMapper.fromCustomer(customer));
		savingAccountDto.setInterestRate(interestRate);
		SavingAccount savedAccount = accountRepository.save(accountMapper.fromSavingAccountDto(savingAccountDto));
		accountMapper.fromSavingAccount(savedAccount);
		log.info("Saving_account saved");
	}

	private Operation initializeOperations(OperationType debit, double amount, String description, Account account) {
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

	private String generateRandomIban(){
		// generate an iban
		String iban = Iban.random(CountryCode.FR).toFormattedString();
		// check if the iban already exists
		boolean ibanExists = accountRepository.findByIban(iban).isPresent();
		// if exists -> generate new random iban
		if(ibanExists){
			generateRandomIban();
		}
		// if not exist -> return generated iban
		return iban;
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

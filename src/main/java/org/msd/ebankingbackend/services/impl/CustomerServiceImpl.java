package org.msd.ebankingbackend.services.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import org.msd.ebankingbackend.dtos.AccountDto;
import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.entities.Account;
import org.msd.ebankingbackend.entities.Customer;
import org.msd.ebankingbackend.mappers.CustomerMapper;
import org.msd.ebankingbackend.repositories.CustomerRepository;
import org.msd.ebankingbackend.services.AccountService;
import org.msd.ebankingbackend.services.CustomerService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	private final AccountService accountService;
	private final EntityValidatorService<CustomerDto> validator;

	@Override
	public CustomerDto save(CustomerDto customerDto) {
		log.info("Customer saved");
		validator.validateInput(customerDto);
		Customer customer = customerMapper.fromCustomerDto(customerDto);
		customer.setCreatedAt(LocalDateTime.now());
		Customer savedCustomer = customerRepository.save(customer);
		return customerMapper.fromCustomer(savedCustomer);
	}

	@Override
	public List<CustomerDto> findAll() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream()
				.map(customerMapper::fromCustomer)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDto findById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Customer not found" + id));
		return customerMapper.fromCustomer(customer);
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		log.info("Customer Updated");
		Customer customer = customerMapper.fromCustomerDto(customerDto);
		Customer savedCustomer = customerRepository.save(customer);
		return customerMapper.fromCustomer(savedCustomer);
	}

	@Override
	public void delete(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Integer validateAccount(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Customer was found for customer account validation"));
		if(customer.getAccounts().isEmpty()){
			// Create a bank account
			AccountDto accountDto = AccountDto.builder()
					.customerDto(customerMapper.fromCustomer(customer)).build();
			var savedAccount = accountService.save(accountDto);
			customer.setAccounts((List<Account>) (Account.builder()
					.id(savedAccount.getId())
					).build()
            );
			throw new EntityNotFoundException("No Account was found for customer account validation");
		}
		return 0;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Integer invalidateAccount(Long id) {
		return 0;
	}

	/*@Override
	public List<CustomerDto> searchCustomers(Long keyword) {
		List<Customer> customers = customerRepository.searchCustomer(keyword);
        return customers.stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
	}*/
}

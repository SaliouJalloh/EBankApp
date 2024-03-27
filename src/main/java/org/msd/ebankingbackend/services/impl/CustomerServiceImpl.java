package org.msd.ebankingbackend.services.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.entities.Customer;
import org.msd.ebankingbackend.exception.CustomerNotFoundException;
import org.msd.ebankingbackend.mappers.CustomerMapper;
import org.msd.ebankingbackend.repositories.CustomerRepository;
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
	private final EntityValidatorService<CustomerDto> validator;

	@Override
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		log.info("Customer saved");
		validator.validateInput(customerDto);
		Customer customer = customerMapper.fromCustomerDto(customerDto);
		customer.setCreatedAt(LocalDateTime.now());
		Customer savedCustomer = customerRepository.save(customer);
		return customerMapper.fromCustomer(savedCustomer);
	}

	@Override
	public List<CustomerDto> findAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
        return customers.stream()
				.map(customerMapper::fromCustomer)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDto findCustomerById(Long id) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
		return customerMapper.fromCustomer(customer);
	}

	/*@Override
	public List<CustomerDto> searchCustomers(Long keyword) {
		List<Customer> customers = customerRepository.searchCustomer(keyword);
        return customers.stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
	}*/

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		log.info("Customer Updated");
		Customer customer = customerMapper.fromCustomerDto(customerDto);
		Customer savedCustomer = customerRepository.save(customer);
		return customerMapper.fromCustomer(savedCustomer);
	}
	@Override
	public void deleteCustomer(Long customerId){
		customerRepository.deleteById(customerId);
	}
}

package org.msd.ebankingbackend.services;

import java.util.List;

import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.exception.CustomerNotFoundException;

public interface CustomerService {
	CustomerDto saveCustomer(CustomerDto customerDto);
	List<CustomerDto> findAllCustomers();
	CustomerDto findCustomerById(Long id) throws CustomerNotFoundException;
	//List<CustomerDto> searchCustomers(Long keyword);
	CustomerDto updateCustomer(CustomerDto customerDto);
	void deleteCustomer(Long id);
}

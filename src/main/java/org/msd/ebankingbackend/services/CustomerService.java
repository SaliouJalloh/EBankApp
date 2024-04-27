package org.msd.ebankingbackend.services;

import org.msd.ebankingbackend.dtos.CustomerDto;

public interface CustomerService extends AbstractService<CustomerDto> {
		//List<CustomerDto> searchCustomers(Long keyword);
	CustomerDto updateCustomer(CustomerDto customerDto);
}

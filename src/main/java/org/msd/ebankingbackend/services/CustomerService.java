package org.msd.ebankingbackend.services;

import org.msd.ebankingbackend.dtos.CustomerDto;

public interface CustomerService extends AbstractService<CustomerDto> {
	Long validateAccount(Long id);
	Long invalidateAccount(Long id);
	CustomerDto updateCustomer(CustomerDto customerDto);
	//List<CustomerDto> searchCustomers(Long keyword);
}

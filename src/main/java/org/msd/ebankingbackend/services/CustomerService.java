package org.msd.ebankingbackend.services;

import org.msd.ebankingbackend.dtos.AuthenticationRequest;
import org.msd.ebankingbackend.dtos.AuthenticationResponse;
import org.msd.ebankingbackend.dtos.CustomerDto;

public interface CustomerService extends AbstractService<CustomerDto> {
	Long validateAccount(Long id);
	Long invalidateAccount(Long id);
	CustomerDto updateCustomer(CustomerDto customerDto);

    AuthenticationResponse register(CustomerDto customerDto);

	AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
	//List<CustomerDto> searchCustomers(Long keyword);
}

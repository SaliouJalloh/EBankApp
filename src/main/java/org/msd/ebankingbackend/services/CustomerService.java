package org.msd.ebankingbackend.services;

import org.msd.ebankingbackend.storage.models.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Long id);
}

package org.msd.ebankingbackend.storage.persistences;

import org.msd.ebankingbackend.services.payload.request.RegisterRequest;
import org.msd.ebankingbackend.storage.models.Customer;

import java.util.List;

public interface ICustomerPersistenceService {

    Customer findCustomerById(Long id);

    List<Customer> findAllCustomers();

    Customer updateCustomer(Customer customer);

    void deleteCustomerById(Long id);

    Long validateAccount(Long id);

    Long invalidateAccount(Long id);

    Customer saveCustomerWithRole(RegisterRequest request);

    Customer saveCustomer(Customer customer);

    Customer findCustomerByEmail(String email);

    boolean existsCustomerByEmail(String email);
}

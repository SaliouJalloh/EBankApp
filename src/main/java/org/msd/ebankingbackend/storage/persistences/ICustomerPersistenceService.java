package org.msd.ebankingbackend.storage.persistences;

import org.msd.ebankingbackend.storage.models.Customer;

public interface ICustomerPersistenceService {
    Customer findById(Long id);

    void saveCustomerWithRole(Customer customer);

    Customer findCustomerByEmail(String email);

    boolean existsCustomerByEmail(String email);
}

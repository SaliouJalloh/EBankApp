package org.msd.ebankingbackend.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.service.ICustomerService;
import org.msd.ebankingbackend.storage.entities.CustomerEntity;
import org.msd.ebankingbackend.storage.mappers.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.storage.models.Customer;
import org.msd.ebankingbackend.storage.persistences.ICustomerPersistenceService;
import org.msd.ebankingbackend.validator.EntityValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerPersistenceService customerPersistenceService;
    private final EntityValidatorService<Customer> validator;
    private final ICustomerPersistenceMapper customerPersistenceMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer: {}", customer);
        validator.validateInput(customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        CustomerEntity customerEntity = customerPersistenceMapper.toEntity(customer);
        if (customerPersistenceService.existsCustomerByEmail(customer.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "existe deja");
        }
        customerPersistenceService.saveCustomer(customer);
        return customerPersistenceMapper.toModel(customerEntity);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerPersistenceService.findCustomerByEmail(email);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerPersistenceService.findCustomerById(id);
    }
}

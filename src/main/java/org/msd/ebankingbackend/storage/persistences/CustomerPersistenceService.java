package org.msd.ebankingbackend.storage.persistences;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.storage.entities.CustomerEntity;
import org.msd.ebankingbackend.storage.entities.RoleEntity;
import org.msd.ebankingbackend.storage.mappers.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.storage.models.Customer;
import org.msd.ebankingbackend.storage.repositories.CustomerRepository;
import org.msd.ebankingbackend.storage.repositories.RoleRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerPersistenceService implements ICustomerPersistenceService {

    private static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    private final ICustomerPersistenceMapper customerPersistenceMapper;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Override
    public Customer findById(Long id) {
        CustomerEntity userEntity = customerRepository.findById(id).orElseThrow(() -> {
            log.error("Customer with id {} not found", id);
            return new EntityNotFoundException("Not user found with id: " + id);
        });
        return customerPersistenceMapper.toModel(userEntity);
    }

    @Override
    public void saveCustomerWithRole(Customer customer) {
        RoleEntity role = roleRepository.findByName(ROLE_CUSTOMER).orElseThrow(() -> {
            log.error("Role with name {} not found", ROLE_CUSTOMER);
            return new EntityNotFoundException("Not role found with name: " + ROLE_CUSTOMER);
        });
        CustomerEntity customerEntity = customerPersistenceMapper.toEntity(customer);
        customerEntity.setRole(role);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        customerPersistenceMapper.toModel(savedCustomer);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email).orElseThrow(() -> {
            log.error("Customer with email {} not found", email);
            return new EntityNotFoundException("Not user found with email: " + email);
        });
        return customerPersistenceMapper.toModel(customerEntity);
    }

    @Override
    public boolean existsCustomerByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}

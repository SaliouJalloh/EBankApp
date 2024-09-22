package org.msd.ebankingbackend.services.auth;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.services.dtos.AuthenticationRequest;
import org.msd.ebankingbackend.services.dtos.AuthenticationResponse;
import org.msd.ebankingbackend.storage.models.Customer;
import org.msd.ebankingbackend.storage.persistences.ICustomerPersistenceService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Authentication implements IAuthentication {

    private final ICustomerPersistenceService customerPersistenceService;
    private final PasswordEncoder passwordEncoder;
    private final EntityValidatorService<Customer> validator;

    @Transactional
    @Override
    public AuthenticationResponse register(Customer customer) {
        log.info("Registering new customer: {}", customer);
        validator.validateInput(customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerPersistenceService.saveCustomerWithRole(customer);
        return AuthenticationResponse.builder().build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        log.info("Authenticating user: {}", authenticationRequest);
        return AuthenticationResponse.builder().build();
    }
}

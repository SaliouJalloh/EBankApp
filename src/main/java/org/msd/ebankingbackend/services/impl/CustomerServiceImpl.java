package org.msd.ebankingbackend.services.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.services.CustomerService;
import org.msd.ebankingbackend.storage.entities.CustomerEntity;
import org.msd.ebankingbackend.storage.mappers.ICustomerPersistenceMapper;
import org.msd.ebankingbackend.storage.models.Customer;
import org.msd.ebankingbackend.storage.persistences.ICustomerPersistenceService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

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
        customerPersistenceService.saveCustomerWithRole(customer);
        return customerPersistenceMapper.toModel(customerEntity);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerPersistenceService.findCustomerByEmail(email);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerPersistenceService.findById(id);
    }

    // Méthode qui permet de charger un utilisateur par son nom d'utilisateur.
    // Cette méthode est appelée par Spring Security lorsqu'un utilisateur tente de se connecter.
    // Elle doit retourner un objet UserDetails qui contient les informations de l'utilisateur
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Nous récupérons l'utilisateur par son nom d'utilisateur
        Customer customer = customerPersistenceService.findCustomerByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        // Puis nous retournons un objet UserDetails qui contient les informations de l'utilisateur
        // Nous utilisons la classe org.springframework.security.core.userdetails.User pour créer cet objet
        // Cette classe est fournie par Spring Security et implémente l'interface UserDetails
        // Elle contient les informations de l'utilisateur nécessaires à l'authentification
        return User.builder()
                .username(customer.getEmail())
                .password(customer.getPassword())
                .roles(String.valueOf(customer.getRole()))
                .build();
    }
}

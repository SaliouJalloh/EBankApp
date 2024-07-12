package org.msd.ebankingbackend.services.impl;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.msd.ebankingbackend.config.JwtUtils;
import org.msd.ebankingbackend.dtos.AccountDto;
import org.msd.ebankingbackend.dtos.AuthenticationRequest;
import org.msd.ebankingbackend.dtos.AuthenticationResponse;
import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.entities.Account;
import org.msd.ebankingbackend.entities.Customer;
import org.msd.ebankingbackend.entities.Role;
import org.msd.ebankingbackend.mappers.CustomerMapper;
import org.msd.ebankingbackend.repositories.CustomerRepository;
import org.msd.ebankingbackend.repositories.RoleRepository;
import org.msd.ebankingbackend.services.AccountService;
import org.msd.ebankingbackend.services.CustomerService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountService accountService;
    private final EntityValidatorService<CustomerDto> validator;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        validator.validateInput(customerDto);
        Customer customer = customerMapper.fromCustomerDto(customerDto);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer saved");
        return customerMapper.fromCustomer(savedCustomer);
    }

    @Override
    @Transactional
    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found" + id));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        log.info("Customer Updated");
        Customer customer = customerMapper.fromCustomerDto(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.fromCustomer(savedCustomer);
    }

    @Override
    @Transactional
    public AuthenticationResponse register(CustomerDto customerDto) {
        validator.validateInput(customerDto);
        Customer customer = customerMapper.fromCustomerDto(customerDto);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(findOrCreateRole(ROLE_CUSTOMER));
        Customer savedCustomer = customerRepository.save(customer);
        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", savedCustomer.getId());
        claims.put("fullName", savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
        String token = jwtUtils.generateToken(savedCustomer, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        final Customer customer = customerRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", customer.getId());
        claims.put("fullName", customer.getFirstName() + " " + customer.getLastName());

        final String token = jwtUtils.generateToken(customer, claims);

        return AuthenticationResponse.builder().token(token).build();
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public Long validateAccount(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Customer was found for customer account validation"));
        if (customer.getAccounts().isEmpty()) {
            // Create a bank account
            AccountDto accountDto = AccountDto.builder()
                    .customerDto(customerMapper.fromCustomer(customer)).build();
            AccountDto savedAccount = accountService.save(accountDto);
            customer.setAccounts(
                    (List<Account>) Account.builder()
                            .id(savedAccount.getId())
                            .build()
            );
        }
        customer.setActive(true);
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public Long invalidateAccount(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Customer was found for customer account invalidation"));
        customer.setActive(false);
        customerRepository.save(customer);
        return customer.getId();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            return roleRepository.save(Role.builder()
                    .name(roleName)
                    .build());
        }
        return role;
    }

	/*@Override
	public List<CustomerDto> searchCustomers(Long keyword) {
		List<Customer> customers = customerRepository.searchCustomer(keyword);
        return customers.stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
	}*/
}

package org.msd.ebankingbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.services.CustomerService;
import org.msd.ebankingbackend.storage.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
}

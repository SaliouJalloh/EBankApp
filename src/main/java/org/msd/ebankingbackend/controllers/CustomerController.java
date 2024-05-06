package org.msd.ebankingbackend.controllers;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
	
	private final CustomerService customerService;
    private static final String ID = "/{customerId}";
	
	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
		return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.CREATED);
	}
	
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(ID)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) throws EntityNotFoundException {
    	return ResponseEntity.ok(customerService.findById(customerId));
    }

    @PutMapping(ID)
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto){
        customerDto.setId(customerId);
        return new ResponseEntity<>(customerService.updateCustomer(customerDto), HttpStatus.OK);
    }

    @DeleteMapping(ID)
    public void deleteCustomer(@PathVariable Long customerId){
        customerService.delete(customerId);
    }

    /*@GetMapping("/search")
    public List<CustomerDto> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") Long keyword){
        return customerService.searchCustomers(keyword);
    }*/
}

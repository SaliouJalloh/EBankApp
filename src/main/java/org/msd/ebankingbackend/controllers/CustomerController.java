package org.msd.ebankingbackend.controllers;

import java.util.List;

import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.exception.CustomerNotFoundException;
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
    private static final String ID = "/{id}";
	
	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
		return new ResponseEntity<>(customerService.saveCustomer(customerDto), HttpStatus.CREATED);
	}
	
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(){
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }
    
    @GetMapping(ID)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
    	return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @PutMapping(ID)
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        customerDto.setId(id);
        return new ResponseEntity<>(customerService.updateCustomer(customerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    /*@GetMapping("/search")
    public List<CustomerDto> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") Long keyword){
        return customerService.searchCustomers(keyword);
    }*/
}

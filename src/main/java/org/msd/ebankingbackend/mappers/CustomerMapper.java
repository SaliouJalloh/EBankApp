package org.msd.ebankingbackend.mappers;

import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
	
	 public CustomerDto fromCustomer(Customer customer){
	        CustomerDto customerDto = new CustomerDto();
	        BeanUtils.copyProperties(customer,customerDto);
	        return  customerDto;
	    }
	 
	    public Customer fromCustomerDto(CustomerDto customerDto){
	        Customer customer = new Customer();
	        BeanUtils.copyProperties(customerDto,customer);
	        return  customer;
	    }

}

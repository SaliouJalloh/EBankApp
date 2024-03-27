package org.msd.ebankingbackend.dtos;

import org.msd.ebankingbackend.entities.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressDto extends AbstractEntityDto {

    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String county;
    private Customer customer;
}

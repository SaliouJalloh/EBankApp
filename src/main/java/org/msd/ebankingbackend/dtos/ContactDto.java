package org.msd.ebankingbackend.dtos;

import org.msd.ebankingbackend.entities.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactDto extends AbstractEntityDto {

    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    private Customer customer;
}

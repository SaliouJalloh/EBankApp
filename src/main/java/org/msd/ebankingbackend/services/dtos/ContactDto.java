package org.msd.ebankingbackend.services.dtos;

import lombok.Getter;
import lombok.Setter;
import org.msd.ebankingbackend.storage.entities.CustomerEntity;

@Getter
@Setter
public class ContactDto extends AbstractEntityDto {

    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    private CustomerEntity customerEntity;
}

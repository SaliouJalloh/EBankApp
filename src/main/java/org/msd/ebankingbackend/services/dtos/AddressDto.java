package org.msd.ebankingbackend.services.dtos;

import lombok.Getter;
import lombok.Setter;
import org.msd.ebankingbackend.storage.entities.CustomerEntity;

@Getter
@Setter
public class AddressDto extends AbstractEntityDto {

    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String county;
    private CustomerEntity customerEntity;
}

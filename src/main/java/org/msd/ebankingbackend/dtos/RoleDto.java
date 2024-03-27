package org.msd.ebankingbackend.dtos;

import org.msd.ebankingbackend.entities.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoleDto extends AbstractEntityDto {

    private String name;
    private Customer customer;
}

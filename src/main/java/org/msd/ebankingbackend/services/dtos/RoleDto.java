package org.msd.ebankingbackend.services.dtos;

import lombok.Getter;
import lombok.Setter;
import org.msd.ebankingbackend.storage.entities.CustomerEntity;

@Getter
@Setter
public class RoleDto extends AbstractEntityDto {

    private String name;
    private CustomerEntity customerEntity;
}

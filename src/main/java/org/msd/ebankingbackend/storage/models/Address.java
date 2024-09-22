package org.msd.ebankingbackend.storage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractModel {

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String county;

    private Customer customer;
}

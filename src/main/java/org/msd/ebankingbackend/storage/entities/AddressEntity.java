package org.msd.ebankingbackend.storage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
@Entity(name = "address")
public class AddressEntity extends AbstractEntity {

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String county;

    @OneToOne
    private CustomerEntity customer;
}

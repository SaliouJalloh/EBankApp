package org.msd.ebankingbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Address extends AbstractEntity {

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String county;

    @CreatedBy
    @OneToOne
    private Customer customer;
}

package org.msd.ebankingbackend.storage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
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
@Entity(name = "contact")
public class ContactEntity extends AbstractEntity {

    private String firstname;
    private String lastname;

    @Email
    private String email;

    private String iban;

    @ManyToOne
    private CustomerEntity customer;
}

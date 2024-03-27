package org.msd.ebankingbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
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
public class Contact extends AbstractEntity {

    private String firstname;
    private String lastname;

    @Email
    private String email;

    private String iban;

    @CreatedBy
    @ManyToOne
    private Customer customer;
}

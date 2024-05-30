package org.msd.ebankingbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter @Setter
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer extends AbstractEntity {

    private String firstName;
    private String lastName;

    @Email
    private String email;

    private String password;
    private boolean active;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToMany(mappedBy = "customer")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "customer")
    private List<Operation> operations;

    @OneToOne
    private Role role;
}

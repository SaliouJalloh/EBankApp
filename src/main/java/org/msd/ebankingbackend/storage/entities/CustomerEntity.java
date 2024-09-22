package org.msd.ebankingbackend.storage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class CustomerEntity extends AbstractEntity {

    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String password;

    private boolean active;

    @OneToOne
    private AddressEntity address;

    @OneToMany(mappedBy = "customer")
    private List<AccountEntity> accounts;

    @OneToMany(mappedBy = "customer")
    private List<ContactEntity> contacts;

    @OneToMany(mappedBy = "customer")
    private List<OperationEntity> operations;

    @OneToOne(cascade = ALL)
    private RoleEntity role;

}

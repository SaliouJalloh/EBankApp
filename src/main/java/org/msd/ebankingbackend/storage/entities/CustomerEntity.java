package org.msd.ebankingbackend.storage.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class CustomerEntity extends AbstractEntity {

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
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

    @OneToOne
    private RoleEntity role;

}

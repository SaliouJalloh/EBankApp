package org.msd.ebankingbackend.storage.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.msd.ebankingbackend.storage.enums.AccountStatus;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Entity(name = "accounts")
public class AccountEntity extends AbstractEntity {

    private String iban;

    private double balance;

    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;

    private String currency;

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany(mappedBy = "account")
    private List<OperationEntity> operation;
}

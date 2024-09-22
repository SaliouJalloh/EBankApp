package org.msd.ebankingbackend.storage.models;


import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
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
@Table(name = "accounts")
public class Account extends AbstractModel {

    private String iban;

    private double balance;

    private AccountStatus status;

    private String currency;


    private Customer customer;

    private List<Operation> operations;
}

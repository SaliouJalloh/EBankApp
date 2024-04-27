package org.msd.ebankingbackend.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.msd.ebankingbackend.enums.AccountStatus;
import org.springframework.data.annotation.CreatedBy;

import java.util.List;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Table(name = "accounts")
@Entity
public class Account extends AbstractEntity {

    private String iban;

    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    
    private String currency;

    @CreatedBy
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;
}

package org.msd.ebankingbackend.storage.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.msd.ebankingbackend.storage.enums.OperationType;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "operation")
public class OperationEntity extends AbstractEntity {

    private double amount;

    private String destinationIban;

    @Column(nullable = false, updatable = false)
    private LocalDateTime operationDate;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    private AccountEntity account;

    @ManyToOne
    private CustomerEntity customer;
}

package org.msd.ebankingbackend.storage.models;

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
public class Operation extends AbstractModel {

    private double amount;

    private String destinationIban;

    private LocalDateTime operationDate;

    private OperationType type;

    private Account account;

    private Customer customer;
}

package org.msd.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.msd.ebankingbackend.enums.OperationType;

import java.time.LocalDateTime;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Operation extends AbstractEntity {

    private double amount;

    private String destinationIban;

    @Column(nullable = false,updatable = false)
    private LocalDateTime operationDate;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    private Account account;
    
    private String description;
}

package org.msd.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("SA")
@Entity
public class SavingAccount extends Account{
    private double interestRate;
}

package org.msd.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("CA")
@Entity
public class CurrentAccount extends Account{
    private double overDraft;
}

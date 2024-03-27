package org.msd.ebankingbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Role extends AbstractEntity {

    private String name;

    @CreatedBy
    @OneToOne
    private Customer customer;
}

package org.msd.ebankingbackend.services.dtos;


import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntityDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedDate;
}

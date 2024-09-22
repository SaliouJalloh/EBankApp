package org.msd.ebankingbackend.storage.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractModel {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedDate;
}

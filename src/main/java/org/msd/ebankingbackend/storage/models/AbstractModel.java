package org.msd.ebankingbackend.storage.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractModel {

    private Long id;

    private Date createdAt;

    private Date lastModifiedDate;
}

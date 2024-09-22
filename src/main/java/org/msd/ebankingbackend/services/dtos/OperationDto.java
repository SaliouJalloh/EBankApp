package org.msd.ebankingbackend.services.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.msd.ebankingbackend.storage.entities.AccountEntity;
import org.msd.ebankingbackend.storage.enums.OperationType;

import java.util.Date;

@Getter
@Setter
public class OperationDto extends AbstractEntityDto {

    @Positive
    private double amount;
    private Date operationDate;
    private OperationType type;
    private AccountEntity accountEntity;
    private String description;
}


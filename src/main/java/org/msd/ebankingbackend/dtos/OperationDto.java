package org.msd.ebankingbackend.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.msd.ebankingbackend.entities.Account;
import org.msd.ebankingbackend.enums.OperationType;

import java.util.Date;

@Getter @Setter
public class OperationDto extends AbstractEntityDto{

    @Positive
    private double amount;
    private Date operationDate;
    private OperationType type;
    private Account account;
    private String description;
}


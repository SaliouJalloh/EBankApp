package org.msd.ebankingbackend.services.dtos;

import lombok.Data;
import org.msd.ebankingbackend.storage.enums.OperationType;

import java.util.Date;

@Data
public class AccountOperationDto {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}

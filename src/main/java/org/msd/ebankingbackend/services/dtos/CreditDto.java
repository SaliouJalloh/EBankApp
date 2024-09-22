package org.msd.ebankingbackend.services.dtos;

import lombok.Data;

@Data
public class CreditDto {
    private Long accountId;
    private double amount;
    private String description;
}

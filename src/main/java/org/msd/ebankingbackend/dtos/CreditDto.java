package org.msd.ebankingbackend.dtos;

import lombok.Data;

@Data
public class CreditDto {
    private Long accountId;
    private double amount;
    private String description;
}

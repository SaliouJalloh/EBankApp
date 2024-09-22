package org.msd.ebankingbackend.services.dtos;

import lombok.Data;

@Data
public class DebitDto {
    private Long accountId;
    private double amount;
    private String description;
}

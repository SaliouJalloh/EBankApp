package org.msd.ebankingbackend.services.dtos;

import lombok.Data;

@Data
public class TransferRequestDto {
    private Long accountSource;
    private Long accountDestination;
    private double amount;
    private String description;
}

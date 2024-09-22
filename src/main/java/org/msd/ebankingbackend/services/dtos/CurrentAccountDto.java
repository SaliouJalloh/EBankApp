package org.msd.ebankingbackend.services.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CurrentAccountDto extends AccountDto {
    private double overDraft;
}

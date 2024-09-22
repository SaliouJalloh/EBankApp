package org.msd.ebankingbackend.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.msd.ebankingbackend.storage.enums.AccountStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto extends AbstractEntityDto {

    private String type;
    private String iban;
    private double balance;
    private AccountStatus status;
    private String currency;
    private CustomerDto customerDto;
}

package org.msd.ebankingbackend.dtos;

import lombok.*;
import org.msd.ebankingbackend.enums.AccountStatus;

import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class AccountDto extends AbstractEntityDto{

	private String type;
	private String iban;
	private double balance;
	private AccountStatus status;
	private String currency;
	private CustomerDto customerDto;
}

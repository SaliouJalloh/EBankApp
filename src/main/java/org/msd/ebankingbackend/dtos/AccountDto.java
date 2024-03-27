package org.msd.ebankingbackend.dtos;

import org.msd.ebankingbackend.enums.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public abstract class AccountDto extends AbstractEntityDto{

	private String type;
	private String iban;
	private double balance;
	private AccountStatus status;
	private String currency;
	private CustomerDto customerDto;
}

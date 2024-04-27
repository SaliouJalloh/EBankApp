package org.msd.ebankingbackend.mappers;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.CurrentAccountDto;
import org.msd.ebankingbackend.dtos.SavingAccountDto;
import org.msd.ebankingbackend.entities.CurrentAccount;
import org.msd.ebankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountMapper {

	private final CustomerMapper customerMapper;

	 public SavingAccountDto fromSavingAccount(SavingAccount savingAccount){
	        SavingAccountDto savingAccountDto = new SavingAccountDto();
	        BeanUtils.copyProperties(savingAccount,savingAccountDto);
	        savingAccountDto.setCustomerDto(customerMapper.fromCustomer(savingAccount.getCustomer()));
	        savingAccountDto.setType(savingAccount.getClass().getSimpleName());
	        return savingAccountDto;
	 }

	public SavingAccount fromSavingAccountDto(SavingAccountDto savingBankAccountDto){
		SavingAccount savingAccount = new SavingAccount();
		BeanUtils.copyProperties(savingBankAccountDto,savingAccount);
		savingAccount.setCustomer(customerMapper.fromCustomerDto(savingBankAccountDto.getCustomerDto()));
		return savingAccount;
	}

	public CurrentAccountDto fromCurrentAccount(CurrentAccount currentAccount){
		CurrentAccountDto currentAccountDto = new CurrentAccountDto();
		BeanUtils.copyProperties(currentAccount,currentAccountDto);
		currentAccountDto.setCustomerDto(customerMapper.fromCustomer(currentAccount.getCustomer()));
		currentAccountDto.setType(currentAccount.getClass().getSimpleName());
		return currentAccountDto;
	}

	public CurrentAccount fromCurrentAccountDto(CurrentAccountDto currentAccountDto){
		CurrentAccount currentAccount = new CurrentAccount();
		BeanUtils.copyProperties(currentAccountDto,currentAccount);
		currentAccount.setCustomer(customerMapper.fromCustomerDto(currentAccountDto.getCustomerDto()));
		return currentAccount;
	}

}

package org.msd.ebankingbackend.services.impl;

import org.msd.ebankingbackend.entities.Account;
import org.msd.ebankingbackend.entities.CurrentAccount;
import org.msd.ebankingbackend.entities.SavingAccount;
import org.msd.ebankingbackend.repositories.AccountRepository;
import org.msd.ebankingbackend.services.BankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

	private final AccountRepository accountRepository;

	public void consulter(){

		Account bankAccount=
				accountRepository.findById((long) '2').orElse(null);

		if(bankAccount != null) {
			System.out.println("*****************************");
			System.out.println(bankAccount.getId());
			System.out.println(bankAccount.getBalance());
			System.out.println(bankAccount.getStatus());
			System.out.println(bankAccount.getCreatedAt());
			System.out.println(bankAccount.getCustomer().getFirstName());
			System.out.println(bankAccount.getClass().getSimpleName());

			if (bankAccount instanceof CurrentAccount) {
				System.out.println("Over Draft=>" + ((CurrentAccount) bankAccount).getOverDraft());
			} else if (bankAccount instanceof SavingAccount) {
				System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
			}
			bankAccount.getOperations().forEach(op -> {
				System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
			});
		}
	}
}

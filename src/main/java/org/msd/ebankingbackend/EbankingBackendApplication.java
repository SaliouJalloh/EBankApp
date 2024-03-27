package org.msd.ebankingbackend;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.AccountDto;
import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.dtos.SavingAccountDto;
import org.msd.ebankingbackend.exception.CustomerNotFoundException;
import org.msd.ebankingbackend.services.AccountService;
import org.msd.ebankingbackend.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class EbankingBackendApplication {

    private final AccountService accountService;
    private final CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {
            Stream.of("Hassan", "Imane", "Mohamed", "Saliou").forEach(name -> {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setFirstName(name);
                customerDto.setEmail(name + "@gmail.com");
                customerService.saveCustomer(customerDto);
            });
            // Pour chaque client, on lui crée les 2 types de comptes
            customerService.findAllCustomers().forEach(customer -> {
                try {
                    accountService.saveCurrentAccount(Math.random() * 90000, 9000, customer.getId());
                    accountService.saveSavingAccount(Math.random() * 120000, 5.5, customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.toString();
                }
            });
            List<AccountDto> accounts = accountService.findAllAccounts();
            // Creation de 10 operations (credit ou debit) pour chaque compte (CA|SA)
            for (AccountDto account : accounts) {
                for (int i = 0; i < 10; i++) {
                    long accountId;
                    if (account instanceof SavingAccountDto savingAccountDto) {
                        accountId = savingAccountDto.getId();
                    } else {
                        accountId = account.getId();
                    }
                    accountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    accountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }

    //@Bean
/*    CommandLineRunner start(CustomerRepository customerRepository, AccountRepository accountRepository, OperationRepository operationRepository) {

        return args -> {
            // Ajout de clients
            Stream.of("Hassan", "Yassine", "Aicha").forEach(name -> {
                Customer customer = new Customer();
                customer.setFirstName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });

            // Creation de compte courant pour chaque clients
            customerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                //currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(LocalDate.now());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                currentAccount.setCurrency("USD");
                accountRepository.save(currentAccount);

                // Creation de compte epargne pour chaque clients
                SavingAccount savingAccount = new SavingAccount();
                //savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(LocalDate.now());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                savingAccount.setCurrency("USD");
                accountRepository.save(savingAccount);

            });
            // Creation de 10 operation (credit ou debit) pour chaque compte
            accountRepository.findAll().forEach(acc -> {
                for (int i = 0; i < 10; i++) {
                    Operation accountOperation = new Operation();
                    accountOperation.setOperationDate(LocalDateTime.now());
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setAccount(acc);
                    operationRepository.save(accountOperation);
                }

            });
        };

    }*/

}

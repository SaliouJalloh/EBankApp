package org.msd.ebankingbackend;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.AccountDto;
import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.dtos.SavingAccountDto;
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
                customerService.save(customerDto);
            });
            // Pour chaque client, on lui crée les 2 types de comptes
            customerService.findAll().forEach(customer -> {
                try {
                    accountService.saveCurrentAccount(Math.random() * 90000, 9000, customer.getId());
                    accountService.saveSavingAccount(Math.random() * 120000, 5.5, customer.getId());

                } catch (EntityNotFoundException e) {
                    e.toString();
                }
            });
            List<AccountDto> accounts = accountService.findAll();
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
}

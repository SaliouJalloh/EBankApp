package org.msd.ebankingbackend;

import org.msd.ebankingbackend.storage.entities.RoleEntity;
import org.msd.ebankingbackend.storage.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            RoleEntity role = RoleEntity.builder()
                    .name("ROLE_CUSTOMER")
                    .build();
            roleRepository.save(role);
        };
    }
}

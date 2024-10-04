package org.msd.ebankingbackend;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.storage.entities.RoleEntity;
import org.msd.ebankingbackend.storage.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.msd.ebankingbackend.storage.enums.RoleName.ADMIN;
import static org.msd.ebankingbackend.storage.enums.RoleName.USER;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class EbankingBackendApplication {

    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            RoleEntity role1 = RoleEntity.builder()
                    .name(USER)
                    .build();
            roleRepository.save(role1);
            RoleEntity role2 = RoleEntity.builder()
                    .name(ADMIN)
                    .build();
            roleRepository.save(role2);
        };
    }
}

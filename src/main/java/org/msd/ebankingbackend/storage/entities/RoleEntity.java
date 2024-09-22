package org.msd.ebankingbackend.storage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "role")
public class RoleEntity extends AbstractEntity implements GrantedAuthority {

    private String authority;

    private String name;

    @OneToOne
    private CustomerEntity customer;
}

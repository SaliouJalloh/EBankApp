package org.msd.ebankingbackend.storage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends AbstractModel {

    private String firstname;
    private String lastname;

    private String email;

    private String iban;

    private Customer customer;
}

package org.msd.ebankingbackend.controller.dtos;

import jakarta.validation.constraints.*;

public record CustomerDto(

        @NotNull(message = "Le prenom ne doit pas etre vide")
        @NotEmpty(message = "Le prenom ne doit pas etre vide")
        @NotBlank(message = "Le prenom ne doit pas etre vide")
        String firstName,

        @NotNull(message = "Le nom ne doit pas etre vide")
        @NotEmpty(message = "Le nom ne doit pas etre vide")
        @NotBlank(message = "Le nom ne doit pas etre vide")
        String lastName,

        @NotNull(message = "L'email ne doit pas etre null")
        @NotEmpty(message = "L'email ne doit pas etre vide")
        @NotBlank(message = "L'email ne doit pas etre vide")
        @Email(message = "L'email n'est pas conforme")
        String email,

        @NotNull(message = "Le mot de passe ne doit pas etre vide")
        @NotEmpty(message = "Le mot de passe ne doit pas etre vide")
        @NotBlank(message = "Le mot de passe ne doit pas etre vide")
        @Size(min = 8, max = 16, message = "Le mot de passe doit etre entre 8 et 16 caracteres")
        String password,

        String iban,
        boolean active) {

}

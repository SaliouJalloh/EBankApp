package org.msd.ebankingbackend.services.dtos;


import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}

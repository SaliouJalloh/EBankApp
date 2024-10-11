package org.msd.ebankingbackend.service.payload.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;
}

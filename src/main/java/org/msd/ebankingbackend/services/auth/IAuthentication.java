package org.msd.ebankingbackend.services.auth;


import org.msd.ebankingbackend.services.dtos.AuthenticationRequest;
import org.msd.ebankingbackend.services.dtos.AuthenticationResponse;
import org.msd.ebankingbackend.storage.models.Customer;

public interface IAuthentication {
    AuthenticationResponse register(Customer customer);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}

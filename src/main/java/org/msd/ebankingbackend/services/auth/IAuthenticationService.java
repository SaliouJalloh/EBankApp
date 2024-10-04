package org.msd.ebankingbackend.services.auth;


import org.msd.ebankingbackend.services.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.services.payload.request.RegisterRequest;
import org.msd.ebankingbackend.services.payload.response.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}

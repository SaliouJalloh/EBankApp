package org.msd.ebankingbackend.service.auth;


import org.msd.ebankingbackend.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.service.payload.response.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}

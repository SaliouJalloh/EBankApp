package org.msd.ebankingbackend.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.controllers.dtos.AuthenticationRequestDto;
import org.msd.ebankingbackend.controllers.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.controllers.dtos.CustomerDto;
import org.msd.ebankingbackend.controllers.mappers.IControllerMapper;
import org.msd.ebankingbackend.services.auth.IAuthentication;
import org.msd.ebankingbackend.services.dtos.AuthenticationRequest;
import org.msd.ebankingbackend.services.dtos.AuthenticationResponse;
import org.msd.ebankingbackend.storage.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IControllerMapper controllerMapper;
    private final IAuthentication authentication;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponseDto register(@Valid @RequestBody CustomerDto customerDto) {
        Customer customer = controllerMapper.toCustomer(customerDto);
        AuthenticationResponse authenticationResponse = authentication.register(customer);
        return controllerMapper.toAuthenticationDto(authenticationResponse);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticate(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        AuthenticationRequest request = controllerMapper.toAuthenticationRequest(authenticationRequestDto);
        AuthenticationResponse authenticate = authentication.authenticate(request);
        return controllerMapper.toAuthenticationDto(authenticate);
    }
}

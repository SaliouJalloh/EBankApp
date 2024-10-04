package org.msd.ebankingbackend.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.controllers.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.controllers.mappers.IControllerMapper;
import org.msd.ebankingbackend.services.auth.IAuthenticationService;
import org.msd.ebankingbackend.services.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.services.payload.request.RegisterRequest;
import org.msd.ebankingbackend.services.payload.response.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IControllerMapper controllerMapper;
    private final IAuthenticationService authentication;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponseDto register(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse response = authentication.register(request);
        return controllerMapper.toAuthenticationDto(response);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticate(@Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authentication.authenticate(request);
        return controllerMapper.toAuthenticationDto(response);
    }
}

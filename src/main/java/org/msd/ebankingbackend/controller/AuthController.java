package org.msd.ebankingbackend.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.controller.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.controller.mappers.IControllerMapper;
import org.msd.ebankingbackend.service.auth.IAuthenticationService;
import org.msd.ebankingbackend.service.jwt.JwtService;
import org.msd.ebankingbackend.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.service.payload.response.AuthenticationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IControllerMapper controllerMapper;
    private final IAuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponseDto register(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(response.getAccessToken());
        ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(response);
        return controllerMapper.toAuthenticationDto(response);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticate(@Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(response.getAccessToken());
        ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(response);
        return controllerMapper.toAuthenticationDto(response);
    }
}

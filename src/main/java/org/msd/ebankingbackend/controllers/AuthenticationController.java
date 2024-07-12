package org.msd.ebankingbackend.controllers;


import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.AuthenticationRequest;
import org.msd.ebankingbackend.dtos.AuthenticationResponse;
import org.msd.ebankingbackend.dtos.CustomerDto;
import org.msd.ebankingbackend.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final CustomerService customerService;

    @RequestMapping("/register ")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.register(customerDto));
    }

    @RequestMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(customerService.authenticate(authenticationRequest));
    }
}

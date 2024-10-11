package org.msd.ebankingbackend.service.auth;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.exception.UsernameAlreadyExistsException;
import org.msd.ebankingbackend.service.jwt.IJwtService;
import org.msd.ebankingbackend.service.payload.request.AuthenticationRequest;
import org.msd.ebankingbackend.service.payload.request.RegisterRequest;
import org.msd.ebankingbackend.service.payload.response.AuthenticationResponse;
import org.msd.ebankingbackend.storage.models.Customer;
import org.msd.ebankingbackend.storage.persistences.ICustomerPersistenceService;
import org.msd.ebankingbackend.validator.EntityValidatorService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    public static final String BEARER = "BEARER";
    private final ICustomerPersistenceService customerPersistenceService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EntityValidatorService<RegisterRequest> validator;
    private final EntityValidatorService<AuthenticationRequest> authValidator;
    private final IJwtService jwtService;

    @Transactional
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        log.info("Registering new customer: {}", request);
        validator.validateInput(request);
        if (customerPersistenceService.existsCustomerByEmail(request.getEmail())) {
            throw new UsernameAlreadyExistsException("Customer already exists");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer customer = customerPersistenceService.saveCustomerWithRole(request);
        String token = jwtService.generateToken(customer);
        return AuthenticationResponse.builder().accessToken(token).tokenType(BEARER).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Authenticating customer: {}", request);
        authValidator.validateInput(request);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Customer customer = (Customer) authentication.getPrincipal();
        customer.setActive(true);
        String token = jwtService.generateToken(customer);
        return AuthenticationResponse.builder().accessToken(token).tokenType(BEARER).build();
    }
}

package org.msd.ebankingbackend.services.jwt;

import org.springframework.security.core.userdetails.UserDetails;


//définit des méthodes permettant de: générer, extraire et valider les JWT.
public interface IJwtService {
    String extractUsername(String token);

    String generateToken(UserDetails user);

    boolean isTokenValid(String token, UserDetails userDetails);
}

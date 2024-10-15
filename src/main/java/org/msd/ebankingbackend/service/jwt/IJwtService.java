package org.msd.ebankingbackend.service.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;


//méthodes permettant de: générer, extraire et valider les JWT.
public interface IJwtService {
    String extractUsername(String token);

    String generateToken(UserDetails user);

    boolean isTokenValid(String token, UserDetails userDetails);

    ResponseCookie generateJwtCookie(String jwt);

    String getJwtFromCookies(HttpServletRequest request);

    ResponseCookie getCleanJwtCookie();
}

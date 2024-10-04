package org.msd.ebankingbackend.services.auth;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.storage.models.Customer;
import org.msd.ebankingbackend.storage.persistences.ICustomerPersistenceService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ICustomerPersistenceService customerPersistenceService;

    // Méthode qui permet de charger un utilisateur par son nom d'utilisateur.
    // Cette méthode est appelée par Spring Security lorsqu'un utilisateur tente de se connecter.
    // Elle doit retourner un objet UserDetails qui contient les informations de l'utilisateur
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Nous récupérons l'utilisateur par son nom d'utilisateur
        Customer customer = customerPersistenceService.findCustomerByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        // Puis nous retournons un objet UserDetails qui contient les informations de l'utilisateur
        // Nous utilisons la classe org.springframework.security.core.userdetails.User pour créer cet objet
        // Cette classe est fournie par Spring Security et implémente l'interface UserDetails
        // Elle contient les informations de l'utilisateur nécessaires à l'authentification
        return User.builder()
                .username(customer.getEmail())
                .password(customer.getPassword())
                .roles(String.valueOf(customer.getRole()))
                .build();
    }
}

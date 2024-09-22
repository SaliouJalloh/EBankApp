package org.msd.ebankingbackend.controllers.dtos;


public record AuthenticationRequestDto(
        String email,
        String password) {
}

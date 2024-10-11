package org.msd.ebankingbackend.controller.dtos;


public record AuthenticationRequestDto(
        String email,
        String password) {
}

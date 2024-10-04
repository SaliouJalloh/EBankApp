package org.msd.ebankingbackend.controllers.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.controllers.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.services.payload.response.AuthenticationResponse;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);
}

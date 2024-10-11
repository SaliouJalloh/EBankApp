package org.msd.ebankingbackend.controller.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.controller.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.service.payload.response.AuthenticationResponse;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);
}

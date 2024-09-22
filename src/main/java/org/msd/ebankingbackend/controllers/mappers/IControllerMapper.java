package org.msd.ebankingbackend.controllers.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.msd.ebankingbackend.controllers.dtos.AuthenticationRequestDto;
import org.msd.ebankingbackend.controllers.dtos.AuthenticationResponseDto;
import org.msd.ebankingbackend.controllers.dtos.CustomerDto;
import org.msd.ebankingbackend.services.dtos.AuthenticationRequest;
import org.msd.ebankingbackend.services.dtos.AuthenticationResponse;
import org.msd.ebankingbackend.storage.models.Customer;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IControllerMapper {

    Customer toCustomer(CustomerDto dto);

    AuthenticationResponseDto toAuthenticationDto(AuthenticationResponse response);

    AuthenticationRequest toAuthenticationRequest(AuthenticationRequestDto authenticationRequestDto);
}

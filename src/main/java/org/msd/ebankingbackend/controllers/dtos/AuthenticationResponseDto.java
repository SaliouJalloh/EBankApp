package org.msd.ebankingbackend.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponseDto(

        @JsonProperty("access_token")
        String token) {
}

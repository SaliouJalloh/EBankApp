package org.msd.ebankingbackend.controller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponseDto(

        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("token_type")
        String tokenType
) {
}

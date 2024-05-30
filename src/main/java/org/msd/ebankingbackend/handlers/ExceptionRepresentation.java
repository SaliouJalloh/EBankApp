package org.msd.ebankingbackend.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionRepresentation {

    private String errorMessage;
    private String errorSource;
    private Set<String> validationErrors;

}

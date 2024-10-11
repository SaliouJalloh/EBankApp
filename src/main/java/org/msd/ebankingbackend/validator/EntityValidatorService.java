package org.msd.ebankingbackend.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.exception.EntityValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class EntityValidatorService<T> {

    private final Validator validator;

    public void validateInput(@Valid T entityToValidate) {
        Set<ConstraintViolation<T>> violations = validator.validate(entityToValidate);
        if (!violations.isEmpty()) {
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new EntityValidationException(errorMessages, entityToValidate.getClass().getName());
        }
    }
}



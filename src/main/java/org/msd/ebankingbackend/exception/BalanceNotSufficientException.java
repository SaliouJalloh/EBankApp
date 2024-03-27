package org.msd.ebankingbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BalanceNotSufficientException extends RuntimeException {

	public BalanceNotSufficientException(String message) {
		super(message);
	}

}

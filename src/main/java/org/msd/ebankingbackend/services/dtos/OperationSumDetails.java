package org.msd.ebankingbackend.services.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OperationSumDetails {
    LocalDate getOperationDate();

    BigDecimal getAmount();
}

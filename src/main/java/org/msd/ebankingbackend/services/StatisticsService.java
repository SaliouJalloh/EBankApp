package org.msd.ebankingbackend.services;

import org.msd.ebankingbackend.dtos.OperationSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface StatisticsService {

  List<OperationSumDetails> findSumOperationsByDate(LocalDate startDate, LocalDate endDate, Long customerId);

  BigDecimal getAccountBalance(Long customerId);

  BigDecimal highestDebit(Long customerId);

  BigDecimal highestCredit(Long customerId);

}

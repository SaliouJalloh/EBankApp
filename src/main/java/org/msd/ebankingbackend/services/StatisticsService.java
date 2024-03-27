package org.msd.ebankingbackend.services;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface StatisticsService {

  //List<TransactionSumDetails> findSumTractionsByDate(LocalDate startDate, LocalDate endDate, Integer userId);

  BigDecimal getAccountBalance(Integer userId);

  BigDecimal highestTransfer(Integer userId);

  BigDecimal highestDeposit(Integer userId);

}

package org.msd.ebankingbackend.services;

import java.math.BigDecimal;

public interface IStatisticsService {

//  List<TransactionSumDetails> findSumTractionsByDate(LocalDate startDate, LocalDate endDate, Integer userId);

    BigDecimal getAccountBalance(Integer userId);

    BigDecimal highestTransfer(Integer userId);

    BigDecimal highestDeposit(Integer userId);

}
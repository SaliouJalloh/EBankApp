package org.msd.ebankingbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.enums.OperationType;
import org.msd.ebankingbackend.repositories.OperationRepository;
import org.msd.ebankingbackend.services.StatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

  private final OperationRepository operationRepository;

  /*@Override
  public List<TransactionSumDetails> findSumTractionsByDate(LocalDate startDate, LocalDate endDate, Integer userId) {
    LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
    LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
    return operationRepository.findSumTransactionsByDate(start, end, userId);
  }*/

  @Override
  public BigDecimal getAccountBalance(Integer userId) {
    return null;
  }

  @Override
  public BigDecimal highestTransfer(Integer userId) {
    return null;
  }

  @Override
  public BigDecimal highestDeposit(Integer userId) {
    return null;
  }
}

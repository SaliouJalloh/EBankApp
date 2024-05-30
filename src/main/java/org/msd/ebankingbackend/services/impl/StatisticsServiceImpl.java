package org.msd.ebankingbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.OperationSumDetails;
import org.msd.ebankingbackend.enums.OperationType;
import org.msd.ebankingbackend.repositories.OperationRepository;
import org.msd.ebankingbackend.services.StatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

  private final OperationRepository operationRepository;

  @Override
  public List<OperationSumDetails> findSumOperationsByDate(LocalDate startDate, LocalDate endDate, Long customerId) {
    LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
    LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
    return operationRepository.findSumOperationsByDate(start, end, customerId);
  }

  @Override
  public BigDecimal getAccountBalance(Long customerId) {
    return operationRepository.findAccountBalance(customerId);
  }

  @Override
  public BigDecimal highestDebit(Long customerId) {
    return operationRepository.findHighestAmountByOperationType(customerId, OperationType.DEBIT);
  }

  @Override
  public BigDecimal highestCredit(Long customerId) {
    return operationRepository.findHighestAmountByOperationType(customerId, OperationType.CREDIT);
  }
}

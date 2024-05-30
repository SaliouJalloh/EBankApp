package org.msd.ebankingbackend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.OperationSumDetails;
import org.msd.ebankingbackend.services.StatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Tag(name = "statistics")
public class StatisticsController {

    private final StatisticsService service;

    @GetMapping("/sum-by-date/{customerId}")
    public ResponseEntity<List<OperationSumDetails>> findSumOperationsByDate(@PathVariable("customerId") Long customerId,
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end-date")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return ResponseEntity.ok(service.findSumOperationsByDate(startDate, endDate, customerId));
    }

    @GetMapping("/account-balance/{customerId}")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("customerId") Long customerId){
        return ResponseEntity.ok(service.getAccountBalance(customerId));
    }

    @GetMapping("/highest-transfer/{customerId}")
    public ResponseEntity<BigDecimal> highestTransfer(@PathVariable("customerId") Long customerId){
        return ResponseEntity.ok(service.highestDebit(customerId));
    }

    @GetMapping("/highest-deposit/{customerId}")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable("customerId") Long customerId){
        return ResponseEntity.ok(service.highestCredit(customerId));
    }
}

package org.msd.ebankingbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.*;
import org.msd.ebankingbackend.exception.AccountNotFoundException;
import org.msd.ebankingbackend.exception.BalanceNotSufficientException;
import org.msd.ebankingbackend.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private static final String ID = "/{accountId}";

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.findById(accountId));
    }

    @PutMapping(ID)
    public ResponseEntity<AccountDto> updateCustomer(@PathVariable Long accountId, @RequestBody AccountDto accountDto){
        accountDto.setId(accountId);
        return new ResponseEntity<>(accountService.updateAccount(accountDto), HttpStatus.OK);
    }

    @DeleteMapping(ID)
    public void deleteCustomer(@PathVariable Long accountId){
        accountService.delete(accountId);
    }

    @PostMapping("/debit")
    public DebitDto debit(@RequestBody DebitDto debitDto) throws AccountNotFoundException, BalanceNotSufficientException {
        this.accountService.debit(debitDto.getAccountId(), debitDto.getAmount(), debitDto.getDescription());
        return debitDto;
    }

    @PostMapping("/credit")
    public CreditDto credit(@RequestBody CreditDto creditDto) throws AccountNotFoundException, BalanceNotSufficientException {
        this.accountService.credit(creditDto.getAccountId(), creditDto.getAmount(), creditDto.getDescription());
        return creditDto;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDto transferRequestDto) throws AccountNotFoundException, BalanceNotSufficientException {
        this.accountService.transfer(transferRequestDto.getAccountSource(), transferRequestDto.getAccountDestination(), transferRequestDto.getAmount());
    }

   @GetMapping(ID+"/operations")
    public List<OperationDto> getHistory(@PathVariable Long accountId) {
        return accountService.accountHistory(accountId);
    }

    @GetMapping(ID+"/pageOperations")
    public AccountHistoryDto getAccountHistory(
            @PathVariable Long accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws AccountNotFoundException {
        return accountService.getAccountHistory(accountId, page, size);
    }

}

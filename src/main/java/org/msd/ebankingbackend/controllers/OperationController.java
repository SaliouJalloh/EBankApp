package org.msd.ebankingbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.OperationDto;
import org.msd.ebankingbackend.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {

    private final OperationService service;
    private static final String ID = "/{operationId}";

    @PostMapping("/")
    public ResponseEntity<OperationDto> save(@RequestBody OperationDto operationDto) {
        return ResponseEntity.ok(service.save(operationDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<OperationDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<OperationDto> findById(@PathVariable("transaction-id") Long transactionId, @PathVariable String operationId) {
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(@PathVariable("transaction-id") Long transactionId) {
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}

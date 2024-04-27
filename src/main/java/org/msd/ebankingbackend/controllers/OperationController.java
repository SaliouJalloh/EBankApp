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
    public ResponseEntity<OperationDto> findById(@PathVariable Long operationId) {
        return ResponseEntity.ok(service.findById(operationId));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> delete(@PathVariable Long operationId) {
        service.delete(operationId);
        return ResponseEntity.accepted().build();
    }
}

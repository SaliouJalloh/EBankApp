package org.msd.ebankingbackend.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.OperationDto;
import org.msd.ebankingbackend.entities.Operation;
import org.msd.ebankingbackend.enums.OperationType;
import org.msd.ebankingbackend.mappers.OperationMapper;
import org.msd.ebankingbackend.repositories.OperationRepository;
import org.msd.ebankingbackend.services.OperationService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final EntityValidatorService<OperationDto> validatorService;

    @Override
    public OperationDto save(OperationDto dto) {
        validatorService.validateInput(dto);
        Operation operation = OperationMapper.toOperation(dto);
        double operationMultiplier = getOperationMultiplier(operation.getType());
        double amount = operation.getAmount() * operationMultiplier;
        operation.setAmount(amount);
        Operation savedOperation = operationRepository.save(operation);
        return OperationMapper.toOperationDto(savedOperation);
    }

    @Override
    public List<OperationDto> findAll() {
        return operationRepository.findAll()
                .stream()
                .map(OperationMapper::toOperationDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDto findById(Long id) {
        return operationRepository.findById(id)
                .map(OperationMapper::toOperationDto)
                .orElseThrow(() -> new EntityNotFoundException("No Operation found with the ID : " + id));

    }

    @Override
    public void delete(Long id) {
        operationRepository.deleteById(id);
    }

    @Override
    public List<OperationDto> findAllByCustomerId(Long customerId) {
        return operationRepository.findAllByCustomerId(customerId)
                .stream()
                .map(OperationMapper::toOperationDto)
                .collect(Collectors.toList());
    }

    private int getOperationMultiplier(OperationType type) {
        return OperationType.DEBIT == type ? -1 : 1;
    }

}

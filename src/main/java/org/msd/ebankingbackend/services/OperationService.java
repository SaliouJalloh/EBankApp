package org.msd.ebankingbackend.services;

import org.msd.ebankingbackend.dtos.OperationDto;

import java.util.List;

public interface OperationService extends AbstractService<OperationDto> {
    List<OperationDto> findAllByCustomerId(Long customerId);
}

package org.msd.ebankingbackend.mappers;

import org.msd.ebankingbackend.dtos.OperationDto;
import org.msd.ebankingbackend.entities.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationMapper {

    public static OperationDto toOperationDto(Operation operation) {
        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);
        return operationDto;
    }

    public static Operation toOperation(OperationDto operationDto) {
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDto,operation);
        return operation;
    }
}

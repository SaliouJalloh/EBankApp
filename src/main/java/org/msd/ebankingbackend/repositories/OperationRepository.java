package org.msd.ebankingbackend.repositories;

import org.msd.ebankingbackend.dtos.OperationSumDetails;
import org.msd.ebankingbackend.entities.Operation;
import org.msd.ebankingbackend.enums.OperationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long>{

    List<Operation> findAllByCustomerId(Long customerId);

    List<Operation> findAccountById(Long id);
    Page<Operation> findByAccountIdOrderByOperationDateDesc(Long accountId, Pageable pageable);
    
    @Query("select sum(t.amount) from Operation t where t.customer.id = :customerId")
    BigDecimal findAccountBalance(@Param("customerId") Long customerId);

    @Query("select max(abs(t.amount)) as amount from Operation t where t.customer.id = :customerId and t.type = :operationType")
    BigDecimal findHighestAmountByOperationType(Long customerId, OperationType operationType);

    @Query("select t.operationDate as operationDate, sum(t.amount) as amount from Operation t where t.customer.id = :customerId and t.createdAt "
            + "between :start and :end "
            + "group by t.operationDate")
    List<OperationSumDetails> findSumOperationsByDate(LocalDateTime start, LocalDateTime end, Long customerId);

}

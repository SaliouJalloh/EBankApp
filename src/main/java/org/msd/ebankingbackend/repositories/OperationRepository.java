package org.msd.ebankingbackend.repositories;

import org.msd.ebankingbackend.entities.Operation;
import org.msd.ebankingbackend.enums.OperationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>{
    List<Operation> findAccountById(Long id);
    Page<Operation> findByAccountIdOrderByOperationDateDesc(Long accountId, Pageable pageable);


    /*List<Operation> findAllByUserId(Integer userId);

    //@Query("select sum(t.amount) from Operation t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    //@Query("select max(abs(t.amount)) as amount from Operation t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, OperationType transactionType);

    /*@Query("select t.operationDate as transactionDate, sum(t.amount) as amount from Operation t where t.user.id = :userId and t.createdAt "
            + "between :start and :end "
            + "group by t.operationDate")
    List<TransactionSumDetails> findSumTransactionsByDate(LocalDateTime start, LocalDateTime end, Integer userId);
*/
}

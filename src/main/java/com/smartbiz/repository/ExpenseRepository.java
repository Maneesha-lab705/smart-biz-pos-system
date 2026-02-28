package com.smartbiz.repository;

import com.smartbiz.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByBusinessBusinessId(Long businessId);

    List<Expense> findAllByBusinessBusinessIdAndDateBetween(
            Long businessId, Date from, Date to);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e " +
           "WHERE e.business.businessId = :businessId " +
           "AND e.date BETWEEN :from AND :to")
    Double getTotalExpensesByBusinessAndDateRange(
            @Param("businessId") Long businessId,
            @Param("from") Date from,
            @Param("to") Date to);
}

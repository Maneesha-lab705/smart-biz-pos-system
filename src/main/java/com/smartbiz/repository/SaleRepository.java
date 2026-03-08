package com.smartbiz.repository;

import com.smartbiz.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.Double;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByBusinessBusinessId(Long businessId);

    List<Sale> findAllByBusinessBusinessIdOrderByCreatedAtDesc(Long businessId);

    List<Sale> findAllByBusinessBusinessIdAndCreatedAtBetween(
            Long businessId, Date from, Date to);

    // Total revenue in date range
    @Query("SELECT COALESCE(SUM(si.amount), 0) FROM SaleItem si " +
           "WHERE si.sale.business.businessId = :businessId " +
           "AND si.sale.createdAt BETWEEN :from AND :to")
    Double getTotalRevenueByBusinessAndDateRange(
            @Param("businessId") Long businessId,
            @Param("from") Date from,
            @Param("to") Date to);

    // Total cost in date range (from batch cost price * qty)
    @Query("SELECT COALESCE(SUM(si.qty * b.costPrice), 0) FROM SaleItem si " +
           "JOIN si.batch b " +
           "WHERE si.sale.business.businessId = :businessId " +
           "AND si.sale.createdAt BETWEEN :from AND :to")
    Double getTotalCostByBusinessAndDateRange(
            @Param("businessId") Long businessId,
            @Param("from") Date from,
            @Param("to") Date to);

    // Sales count today
    @Query("SELECT COUNT(s) FROM Sale s " +
           "WHERE s.business.businessId = :businessId " +
           "AND s.createdAt BETWEEN :from AND :to")
    Integer countSalesByBusinessAndDateRange(
            @Param("businessId") Long businessId,
            @Param("from") Date from,
            @Param("to") Date to);

    // Recent 5 sales
    List<Sale> findTop5ByBusinessBusinessIdOrderByCreatedAtDesc(Long businessId);

    // Admin: total sales count across all businesses
    @Query("SELECT COUNT(s) FROM Sale s WHERE s.createdAt BETWEEN :from AND :to")
    Long countAllSalesInRange(
            @Param("from") Date from,
            @Param("to") Date to);
}

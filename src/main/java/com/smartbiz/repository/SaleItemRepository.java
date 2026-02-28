package com.smartbiz.repository;

import com.smartbiz.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    List<SaleItem> findAllBySaleSaleId(Long saleId);

    // Top selling products: returns [productId, productName(?), totalQty, totalRevenue]
    @Query("SELECT si.product.productId, SUM(si.qty), SUM(si.totalAmount) " +
           "FROM SaleItem si " +
           "WHERE si.sale.business.businessId = :businessId " +
           "GROUP BY si.product.productId " +
           "ORDER BY SUM(si.qty) DESC")
    List<Object[]> findTopSellingProducts(@Param("businessId") Long businessId);

    // Total items sold in date range
    @Query("SELECT COALESCE(SUM(si.qty), 0) FROM SaleItem si " +
           "WHERE si.sale.business.businessId = :businessId " +
           "AND si.sale.createdAt BETWEEN :from AND :to")
    Integer getTotalItemsSoldInRange(
            @Param("businessId") Long businessId,
            @Param("from") Date from,
            @Param("to") Date to);
}

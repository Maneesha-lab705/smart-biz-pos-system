package com.smartbiz.dto;

import lombok.*;
import java.lang.Double;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DashboardDTO implements SuperDTO {

    // ── Sales Summary ──────────────────────────
    private Double totalSalesToday;
    private Double totalSalesThisMonth;
    private Double totalSalesAllTime;

    // ── Profit ─────────────────────────────────
    private Double totalProfitThisMonth;
    private Double totalExpensesThisMonth;
    private Double netIncomeThisMonth;

    // ── Inventory ──────────────────────────────
    private Integer totalProducts;
    private Integer totalBatches;
    private Integer lowStockCount;          // qty < 10

    // ── Customers ──────────────────────────────
    private Integer totalCustomers;
    private Integer newCustomersThisMonth;

    // ── Top Products ───────────────────────────
    private List<TopProductDTO> topSellingProducts;

    // ── Recent Sales ───────────────────────────
    private List<RecentSaleDTO> recentSales;

    // ── Inner summary DTOs ─────────────────────
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class TopProductDTO {
        private Long productId;
        private String productName;
        private Integer totalQtySold;
        private Double totalRevenue;
    }

    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class RecentSaleDTO {
        private Long saleId;
        private String invoiceNumber;
        private String customerName;
        private Double amount;
        private String createdAt;
    }
}

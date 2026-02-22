package com.smartbiz.dto;

import lombok.*;
import java.lang.Double;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SalesReportDTO implements SuperDTO {

    private String fromDate;
    private String toDate;
    private Long businessId;

    // ── Totals ─────────────────────────────────
    private Double totalRevenue;
    private Double totalCost;
    private Double totalProfit;
    private Integer totalSalesCount;
    private Integer totalItemsSold;

    // ── Daily Breakdown ────────────────────────
    private List<DailySaleDTO> dailySales;

    // ── Top Products ───────────────────────────
    private List<DashboardDTO.TopProductDTO> topProducts;

    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DailySaleDTO {
        private String date;
        private Double revenue;
        private Integer salesCount;
    }
}

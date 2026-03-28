package com.smartbiz.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminStatsDTO implements SuperDTO {

    // ── System Overview ────────────────────────
    private Integer totalBusinesses;
    private Integer activeBusinesses;
    private Integer inactiveBusinesses;
    private Integer newBusinessesThisMonth;

    // ── Revenue ────────────────────────────────
    private BigDecimal totalPlatformRevenue;

    // ── AI Usage ───────────────────────────────
    private Long totalAiRequestsThisMonth;

    // ── Subscription Breakdown ─────────────────
    private List<SubscriptionStatDTO> subscriptionStats;

    // ── Recent Businesses ──────────────────────
    private List<BusinessDTO> recentBusinesses;

    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class SubscriptionStatDTO {
        private String plan;
        private Integer count;
    }
}

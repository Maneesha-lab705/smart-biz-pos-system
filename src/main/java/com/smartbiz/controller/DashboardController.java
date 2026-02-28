package com.smartbiz.controller;

import com.smartbiz.dto.DashboardDTO;
import com.smartbiz.dto.SalesReportDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * GET /api/dashboard/{businessId}
     * Returns full dashboard overview: sales, profit, inventory, customers, top products
     */
    @GetMapping("/{businessId}")
    public ResponseEntity<ApiResponse<DashboardDTO>> getDashboard(@PathVariable Long businessId) {
        return ResponseEntity.ok(ApiResponse.success(dashboardService.getDashboard(businessId)));
    }

    /**
     * GET /api/dashboard/{businessId}/report?from=2025-01-01&to=2025-01-31
     * Returns detailed sales report for a date range
     */
    @GetMapping("/{businessId}/report")
    public ResponseEntity<ApiResponse<SalesReportDTO>> getSalesReport(
            @PathVariable Long businessId,
            @RequestParam String from,
            @RequestParam String to) {
        return ResponseEntity.ok(ApiResponse.success(dashboardService.getSalesReport(businessId, from, to)));
    }
}

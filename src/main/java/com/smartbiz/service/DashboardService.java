package com.smartbiz.service;

import com.smartbiz.dto.DashboardDTO;
import com.smartbiz.dto.SalesReportDTO;

public interface DashboardService {
    DashboardDTO getDashboard(Long businessId);
    SalesReportDTO getSalesReport(Long businessId, String from, String to);
}

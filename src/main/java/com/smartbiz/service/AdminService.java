package com.smartbiz.service;

import com.smartbiz.dto.AdminStatsDTO;
import com.smartbiz.dto.BusinessDTO;
import java.util.List;

public interface AdminService {
    AdminStatsDTO getSystemStats();
    List<BusinessDTO> getAllBusinesses();
    BusinessDTO updateBusinessStatus(Long businessId, String status);
    BusinessDTO updateSubscriptionPlan(Long businessId, String plan);
    void deleteBusiness(Long businessId);
}

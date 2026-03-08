package com.smartbiz.service;

import com.smartbiz.dto.AdminStatsDTO;
import com.smartbiz.dto.BusinessDTO;
import com.smartbiz.dto.UserDTO;
import java.util.List;

public interface AdminService {
    AdminStatsDTO getSystemStats();
    List<BusinessDTO> getAllBusinesses();
    List<UserDTO> getAllUsers();
    BusinessDTO updateBusinessStatus(Long businessId, String status);
    BusinessDTO updateSubscriptionPlan(Long businessId, String plan);
    void deleteBusiness(Long businessId);
}

package com.smartbiz.service.impl;

import com.smartbiz.dto.AdminStatsDTO;
import com.smartbiz.dto.BusinessDTO;
import com.smartbiz.dto.UserDTO;
import com.smartbiz.entity.Business;
import com.smartbiz.exception.ResourceNotFoundException;
import com.smartbiz.mapper.BusinessMapper;
import com.smartbiz.mapper.UserMapper;
import com.smartbiz.repository.BusinessRepository;
import com.smartbiz.repository.SaleRepository;
import com.smartbiz.repository.UserRepository;
import com.smartbiz.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper     businessMapper;
    private final SaleRepository     saleRepository;
    private final UserRepository     userRepository;
    private final UserMapper         userMapper;

    @Override
    public AdminStatsDTO getSystemStats() {
        List<Business> all = businessRepository.findAll();

        // Count active/inactive
        long active   = all.stream().filter(b -> "ACTIVE".equalsIgnoreCase(b.getStatus())).count();
        long inactive = all.size() - active;

        // Calculate start of month
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date monthStart = cal.getTime();

        // New businesses this month
        long newThisMonth = all.stream()
                .filter(b -> b.getCreatedAt() != null && b.getCreatedAt().after(monthStart))
                .count();

        // Subscription breakdown
        Map<String, Long> planMap = all.stream()
                .filter(b -> b.getSubscriptionPlan() != null)
                .collect(Collectors.groupingBy(Business::getSubscriptionPlan, Collectors.counting()));

        List<AdminStatsDTO.SubscriptionStatDTO> subStats = planMap.entrySet().stream()
                .map(e -> AdminStatsDTO.SubscriptionStatDTO.builder()
                        .plan(e.getKey())
                        .count(e.getValue().intValue())
                        .build())
                .collect(Collectors.toList());

        // Recent 5 businesses
        List<BusinessDTO> recentBusinesses = all.stream()
                .sorted((a, b) -> {
                    if (a.getCreatedAt() == null) return 1;
                    if (b.getCreatedAt() == null) return -1;
                    return b.getCreatedAt().compareTo(a.getCreatedAt()) * -1; // descending
                })
                .limit(5)
                .map(businessMapper::toDTO)
                .collect(Collectors.toList());

        // AI requests this month (placeholder)
        long aiRequests = 0L;

        return AdminStatsDTO.builder()
                .totalBusinesses(all.size())
                .activeBusinesses((int) active)
                .inactiveBusinesses((int) inactive)
                .newBusinessesThisMonth((int) newThisMonth)
                .totalPlatformRevenue(BigDecimal.ZERO) // extend with subscription billing
                .totalAiRequestsThisMonth(aiRequests)
                .subscriptionStats(subStats)
                .recentBusinesses(recentBusinesses)
                .build();
    }

    @Override
    public List<BusinessDTO> getAllBusinesses() {
        return businessRepository.findAll().stream()
                .map(businessMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        System.out.println("test");
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessDTO updateBusinessStatus(Long businessId, String status) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new ResourceNotFoundException("Business not found: " + businessId));
        business.setStatus(status);
        return businessMapper.toDTO(businessRepository.save(business));
    }

    @Override
    public BusinessDTO updateSubscriptionPlan(Long businessId, String plan) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new ResourceNotFoundException("Business not found: " + businessId));
        business.setSubscriptionPlan(plan);
        return businessMapper.toDTO(businessRepository.save(business));
    }

    @Override
    public void deleteBusiness(Long businessId) {
        if (!businessRepository.existsById(businessId))
            throw new ResourceNotFoundException("Business not found: " + businessId);
        businessRepository.deleteById(businessId);
    }
}

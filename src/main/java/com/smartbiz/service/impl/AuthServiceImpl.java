package com.smartbiz.service.impl;

import com.smartbiz.dto.auth.*;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.ENUM.AdminRole;
import com.smartbiz.entity.ENUM.BussinessRole;
import com.smartbiz.exception.BusinessException;
import com.smartbiz.exception.ResourceNotFoundException;
import com.smartbiz.mapper.BusinessMapper;
import com.smartbiz.repository.BusinessRepository;
import com.smartbiz.security.JwtUtil;
import com.smartbiz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.smartbiz.dto.UserDTO;
import com.smartbiz.dto.auth.AdminRegisterRequest;
import com.smartbiz.entity.User;
import com.smartbiz.mapper.UserMapper;
import com.smartbiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final UserRepository  userRepository;
    private final UserMapper      userMapper;

    @Value("${smartbiz.admin.secret-key}")
    private String adminSecretKey;

    @Override
    public LoginResponse login(LoginRequest request) {

        // 1. Users table එකෙන් find කරනවා (Admin + Owner + Staff)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + request.getEmail()));

        // 2. Password check
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BusinessException("Invalid credentials");

        // 3. Business name get කරනවා
        String businessName = "SmartBiz Admin";
        Long businessId = null;

        if (user.getBusiness() != null) {
            businessName = user.getBusiness().getBusinessName();
            businessId   = user.getBusiness().getBusinessId();
        }

        // 4. Token generate
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getBussinessRole(),
                user.getAdminRole(),
                businessId
        );

        return LoginResponse.builder()
                .token(token)
                .bussinessRole(user.getBussinessRole())
                .adminRole(user.getAdminRole())
                .businessId(businessId)
                .businessName(businessName)
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

//    @Override
//    public LoginResponse login(LoginRequest request) {
//        Business business = businessRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new ResourceNotFoundException("Business not found with email: " + request.getEmail()));
//
//        if (!passwordEncoder.matches(request.getPassword(), business.getPassword())) {
//            throw new BusinessException("Invalid credentials");
//        }
//
//        String token = jwtUtil.generateToken(business.getEmail(), "OWNER", business.getBusinessId());
//
//        return LoginResponse.builder()
//                .token(token)
//                .role("OWNER")
//                .businessId(business.getBusinessId())
//                .businessName(business.getBusinessName())
//                .email(business.getEmail())
//                .build();
//    }

    @Override
    public LoginResponse register(RegisterRequest request) {
        if (businessRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already registered: " + request.getEmail());
        }

        Business business = new Business();
        business.setBusinessName(request.getBusinessName());
        business.setOwnerName(request.getOwnerName());
        business.setEmail(request.getEmail());
        business.setPassword(passwordEncoder.encode(request.getPassword()));
        business.setContactNumber(request.getContactNumber());
        business.setSubscriptionPlan(request.getSubscriptionPlan() != null ? request.getSubscriptionPlan() : "FREE");
        business.setStatus("ACTIVE");
        business.setCreatedAt(new Date());

        Business saved = businessRepository.save(business);

        // User save කරනවා (OWNER)
        User owner = User.builder()
                .name(request.getOwnerName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .bussinessRole(BussinessRole.OWNER)
                .adminRole(AdminRole.NONE)
                .business(saved)
                .createdAt(new Date())
                .build();
        userRepository.save(owner);

        // Token generate — 4 arguments
        String token = jwtUtil.generateToken(
                saved.getEmail(),
                BussinessRole.OWNER,   // ← enum
                AdminRole.NONE,        // ← enum
                saved.getBusinessId()
        );

        return LoginResponse.builder()
                .token(token)
                .bussinessRole(BussinessRole.OWNER)   // ← role වෙනුවට
                .adminRole(AdminRole.NONE)             // ← මේකත් add
                .businessId(saved.getBusinessId())
                .businessName(saved.getBusinessName())
                .email(saved.getEmail())
                .name(saved.getOwnerName())
                .build();
    }
    @Override
    public UserDTO registerAdmin(AdminRegisterRequest request) {

        // 2. Email check
        if (userRepository.existsByEmail(request.getEmail()))
            throw new BusinessException("Email already in use: " + request.getEmail());

        // 3. Role validate
        AdminRole role;
        try {
            role = AdminRole.valueOf(request.getRole()); // "SUPER_ADMIN" → AdminRole.SUPER_ADMIN
        } catch (IllegalArgumentException e) {
            role = AdminRole.ADMIN; // default
        }


        // 4. Save
        User admin = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .adminRole(role)
                .bussinessRole(BussinessRole.NONE)
                .business(null)     // Admin panel users ට business නෑ
                .createdAt(new Date())
                .build();

        return userMapper.toDTO(userRepository.save(admin));
    }
}

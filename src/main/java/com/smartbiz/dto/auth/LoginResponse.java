package com.smartbiz.dto.auth;

import com.smartbiz.entity.ENUM.AdminRole;
import com.smartbiz.entity.ENUM.BussinessRole;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginResponse {
    private String token;
    private BussinessRole bussinessRole;
    private AdminRole adminRole;
    private Long businessId;
    private String businessName;
    private String email;
    private String name;
}
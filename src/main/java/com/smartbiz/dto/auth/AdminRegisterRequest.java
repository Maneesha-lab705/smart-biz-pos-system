package com.smartbiz.dto.auth;

import com.smartbiz.entity.ENUM.AdminRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}
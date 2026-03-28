package com.smartbiz.dto.auth;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {
    private String businessName;
    private String ownerName;
    private String email;
    private String password;
    private String contactNumber;
    private String subscriptionPlan;
    private String role;
}

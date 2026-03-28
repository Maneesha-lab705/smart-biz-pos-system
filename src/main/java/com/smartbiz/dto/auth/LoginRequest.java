package com.smartbiz.dto.auth;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}

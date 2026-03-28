package com.smartbiz.controller;

import com.smartbiz.dto.UserDTO;
import com.smartbiz.dto.auth.*;
import com.smartbiz.dto.BusinessDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Login successful", authService.login(request)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Business registered successfully", authService.register(request)));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<ApiResponse<UserDTO>> registerAdmin(@RequestBody AdminRegisterRequest request) {

        UserDTO admin = authService.registerAdmin(request);
        return ResponseEntity.ok(ApiResponse.success("Admin registered successfully", admin));
    }
}

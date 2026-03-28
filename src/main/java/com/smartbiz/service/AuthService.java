package com.smartbiz.service;

import com.smartbiz.dto.UserDTO;
import com.smartbiz.dto.auth.AdminRegisterRequest;
import com.smartbiz.dto.auth.LoginRequest;
import com.smartbiz.dto.auth.LoginResponse;
import com.smartbiz.dto.auth.RegisterRequest;
import com.smartbiz.dto.BusinessDTO;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse register(RegisterRequest request);

    UserDTO registerAdmin(AdminRegisterRequest request);

}

package com.smartbiz.dto;

import com.smartbiz.entity.ENUM.AdminRole;
import com.smartbiz.entity.ENUM.BussinessRole;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO implements SuperDTO {
    private Long userId;
    private String email;
    private String name;
    private BussinessRole bussinessRole;
    private AdminRole adminRole;
    private Long businessId;      // ← මේක add කරන්න
    private Date createdAt;
}

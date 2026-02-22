package com.smartbiz.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class BusinessDTO implements SuperDTO {
    private Long businessId;
    private String businessName;
    private String ownerName;
    private String contactNumber;
    private String email;
    private String password;
    private String status;
    private String subscriptionPlan;
    private Date createdAt;
}

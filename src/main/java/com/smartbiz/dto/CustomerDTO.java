package com.smartbiz.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerDTO implements SuperDTO {
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private Long businessId;
    private String businessName;
}

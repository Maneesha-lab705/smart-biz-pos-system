package com.smartbiz.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class SupplierDTO implements SuperDTO {
    private Long supplierId;
    private String name;
    private String contact;
    private String email;
}

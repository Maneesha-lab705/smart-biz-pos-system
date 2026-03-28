package com.smartbiz.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class SuppliesBusinessDTO implements SuperDTO {
    private Long id;
    private Long supplierId;
    private String supplierName;
    private Long businessId;
    private String businessName;
    private String status;
    private Double cost;
    private Date relationshipStartDate;
}

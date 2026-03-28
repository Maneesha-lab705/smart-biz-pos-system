package com.smartbiz.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO implements SuperDTO {
    private Long batchId;
    private String batchNumber;
    private String category;
    private Integer qty;
    private BigDecimal costPrice;
    private String name;
    private Date createdAt;
    private Long productId;
    private Long supplierId;
    private String supplierName;

    // ✅ Business fields add කරන්න
    private Long businessId;
    private String businessName;
    private Double sellingPrice;  // add කරන්න
    private String productName;
}

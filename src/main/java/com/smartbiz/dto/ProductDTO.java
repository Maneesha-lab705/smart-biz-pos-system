package com.smartbiz.dto;

import lombok.*;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDTO implements SuperDTO {
    private Long productId;
    private String ProductName;
    private int QTY;
    private String status;
    private Double sellingPrice;
    private Double billingPrice;
    private Long businessId;
    private String businessName;
}

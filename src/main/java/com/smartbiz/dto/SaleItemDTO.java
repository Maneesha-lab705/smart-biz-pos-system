package com.smartbiz.dto;

import lombok.*;
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class SaleItemDTO implements SuperDTO {
    private Long saleItemId;
    private Long saleId;
    private String invoiceNumber;
    private Long productId;
    private Long batchId;
    private String batchNumber;
    private BigDecimal unitPrice;
    private Integer qty;
    private Double totalAmount;
}

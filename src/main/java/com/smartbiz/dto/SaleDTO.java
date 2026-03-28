package com.smartbiz.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class SaleDTO implements SuperDTO {
    private Long saleId;
    private String invoiceNumber;
    private Date createdAt;
    private Long customerId;
    private String customerName;
    private Long businessId;
    private String businessName;
    private Long batchId;
    private List<SaleItemDTO> saleItems;
    private PaymentDTO payment;
}

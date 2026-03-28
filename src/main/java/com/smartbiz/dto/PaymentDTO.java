package com.smartbiz.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class PaymentDTO implements SuperDTO {
    private Long paymentId;
    private Date paidAt;
    private String paymentStatus;
    private String paymentMethod;
    private Double amount;
    private Long saleId;
    private String invoiceNumber;
}

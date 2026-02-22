package com.smartbiz.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ExpenseDTO implements SuperDTO {
    private Long expenseId;
    private String description;
    private Double amount;
    private Date date;
    private String category;
    private Long businessId;
    private String businessName;
}

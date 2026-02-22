package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "batch")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Batch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private String batchNumber;
    private String category;
    private Integer qty;
    private BigDecimal costPrice;
    private String name;
    private Date createdAt;
    private String procustName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // ✅ Business relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;
}

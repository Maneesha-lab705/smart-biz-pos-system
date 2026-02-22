package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_item")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SaleItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleItemId;
    private Integer qty;
    private Double totalAmount;
    private Long batchId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id") private Sale sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id") private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id") private Batch batch;
}

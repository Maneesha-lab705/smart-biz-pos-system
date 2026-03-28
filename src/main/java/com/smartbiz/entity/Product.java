package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String status;
    private Double sellingPrice;
    private Double billingPrice;
    private Integer totalQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id") private Business business;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Batch> batches;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude private List<SaleItem> saleItems;
}

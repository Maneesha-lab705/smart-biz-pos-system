package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Sale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    private String invoiceNumber;
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id") private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id") private Business business;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id") private Batch batch;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @ToString.Exclude private List<SaleItem> saleItems;
    @OneToOne(mappedBy = "sale", cascade = CascadeType.ALL)
    @ToString.Exclude private Payment payment;
}

package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "supplies_business")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SuppliesBusiness {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Double cost;
    private Date relationshipStartDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id") private Business business;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id") private Supplier supplier;
}

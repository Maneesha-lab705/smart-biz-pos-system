package com.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "supplier")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String name;
    private String contact;
    private String email;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @ToString.Exclude private List<Batch> batches;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @ToString.Exclude private List<SuppliesBusiness> suppliesBusinesses;
}

package com.smartbiz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "business")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Business {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessId;
    private String businessName;
    private String ownerName;
    private String contactNumber;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String status;
    private String subscriptionPlan;
    private Date createdAt;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    @ToString.Exclude private List<Customer> customers;
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    @ToString.Exclude private List<Product> products;
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    @ToString.Exclude private List<Sale> sales;


}

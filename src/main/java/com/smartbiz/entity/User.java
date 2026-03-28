package com.smartbiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartbiz.entity.ENUM.AdminRole;
import com.smartbiz.entity.ENUM.BussinessRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BussinessRole bussinessRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AdminRole adminRole;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    private Date updatedAt;

    /* ===============================
       Relationships
       =============================== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = true)
    @JsonIgnore
    private Business business;
}
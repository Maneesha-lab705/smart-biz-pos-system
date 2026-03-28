package com.smartbiz.repository;

import com.smartbiz.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByBusinessBusinessId(Long businessId);
    boolean existsByEmailAndBusinessBusinessId(String email, Long businessId);
    Integer countByBusinessBusinessId(Long businessId);
}

package com.smartbiz.repository;

import com.smartbiz.entity.SuppliesBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuppliesBusinessRepository extends JpaRepository<SuppliesBusiness, Long> {
    List<SuppliesBusiness> findAllByBusinessBusinessId(Long businessId);
    List<SuppliesBusiness> findAllBySupplierSupplierId(Long supplierId);
}

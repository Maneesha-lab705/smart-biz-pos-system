package com.smartbiz.repository;

import com.smartbiz.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByProductProductId(Long productId);
    List<Batch> findAllBySupplierSupplierId(Long supplierId);
    List<Batch> findAllByProductBusinessBusinessId(Long businessId);
    Integer countByProductBusinessBusinessId(Long businessId);
    // Low stock: qty < threshold
    List<Batch> findAllByProductBusinessBusinessIdAndQtyLessThan(Long businessId, Integer threshold);
    Integer countByProductBusinessBusinessIdAndQtyLessThan(Long businessId, Integer threshold);
}

package com.smartbiz.service.impl;

import com.smartbiz.dto.BatchDTO;
import com.smartbiz.entity.Batch;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Product;
import com.smartbiz.entity.Supplier;
import com.smartbiz.repository.BatchRepository;
import com.smartbiz.repository.BusinessRepository;
import com.smartbiz.repository.ProductRepository;
import com.smartbiz.repository.SupplierRepository;
import com.smartbiz.service.BatchService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final BusinessRepository businessRepository;

    @Transactional
    @Override
    public BatchDTO createBatch(BatchDTO dto) {

        // 1️⃣ Business fetch
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        // 2️⃣ Supplier fetch
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product;

        // 3️⃣ Product fetch or create
        if (dto.getProductId() != null) {
            // Existing product
            product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        } else {
            // Check by productName + businessId
            product = productRepository
                    .findByProductNameAndBusinessBusinessId(dto.getProductName(), dto.getBusinessId())
                    .orElseGet(() -> {
                        // Create new product
                        Product newProduct = Product.builder()
                                .productName(dto.getProductName())
                                .sellingPrice(dto.getSellingPrice())
                                .billingPrice(dto.getCostPrice().doubleValue())
                                .status("ACTIVE")
                                .business(business)
                                .totalQty(0)
                                .build();
                        return productRepository.save(newProduct);
                    });
        }

        // 4️⃣ STOCK UPDATE
        int currentQty = product.getTotalQty() == null ? 0 : product.getTotalQty();
        product.setTotalQty(currentQty + dto.getQty());
        product = productRepository.save(product);

        // 5️⃣ Batch save
        Batch batch = Batch.builder()
                .batchNumber(dto.getBatchNumber())
                .category(dto.getCategory())
                .qty(dto.getQty())
                .costPrice(dto.getCostPrice())
                .name(dto.getName())
                .createdAt(new Date())
                .product(product)
                .procustName(dto.getProductName())
                .supplier(supplier)
                .business(business)
                .build();

        batch = batchRepository.save(batch);

        // 6️⃣ Return DTO
        return toDTO(batch);
    }

    // ✅ toDTO
    private BatchDTO toDTO(Batch batch) {
        BatchDTO dto = new BatchDTO();
        dto.setBatchId(batch.getBatchId());
        dto.setBatchNumber(batch.getBatchNumber());
        dto.setCategory(batch.getCategory());
        dto.setQty(batch.getQty());
        dto.setCostPrice(batch.getCostPrice());
        dto.setName(batch.getName());
        dto.setCreatedAt(batch.getCreatedAt());

        // Product
        dto.setProductId(batch.getProduct().getProductId());
        dto.setProductName(batch.getProduct().getProductName());
        dto.setSellingPrice(batch.getProduct().getSellingPrice());

        // Supplier
        dto.setSupplierId(batch.getSupplier().getSupplierId());
        dto.setSupplierName(batch.getSupplier().getName());

        // Business
        dto.setBusinessId(batch.getBusiness().getBusinessId());
        dto.setBusinessName(batch.getBusiness().getBusinessName());

        return dto;
    }
}
package com.smartbiz.service.impl;

import com.smartbiz.dto.SupplierDTO;
import com.smartbiz.entity.Supplier;
import com.smartbiz.repository.SupplierRepository;
import com.smartbiz.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierDTO createSupplier(SupplierDTO dto) {
        Supplier supplier = Supplier.builder()
                .name(dto.getName())
                .contact(dto.getContact())
                .email(dto.getEmail())
                .build();

        Supplier saved = supplierRepository.save(supplier);
        return toDTO(saved);
    }

    private SupplierDTO toDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierId(supplier.getSupplierId());
        dto.setName(supplier.getName());
        dto.setContact(supplier.getContact());
        dto.setEmail(supplier.getEmail());
        return dto;
    }
}

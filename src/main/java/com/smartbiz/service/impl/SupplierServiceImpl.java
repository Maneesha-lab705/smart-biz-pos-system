package com.smartbiz.service.impl;

import com.smartbiz.dto.SuperDTO;
import com.smartbiz.dto.SupplierDTO;
import com.smartbiz.entity.Supplier;
import com.smartbiz.repository.SupplierRepository;
import com.smartbiz.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    // DTO -> Entity
    private Supplier toEntity(SupplierDTO dto) {
        return Supplier.builder()
                .name(dto.getName())
                .contact(dto.getContact())
                .email(dto.getEmail())
                .build();
    }

    // Entity -> DTO
    private SupplierDTO toDTO(Supplier supplier) {
        return new SupplierDTO(
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getEmail()
        );
    }
    @Override
    public SuperDTO createSupplier(SupplierDTO dto) {
        Supplier supplier = toEntity(dto);       // DTO -> Entity
        Supplier saved = supplierRepository.save(supplier);  // DB save
        return toDTO(saved);                     // Entity -> DTO return
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SuperDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return toDTO(supplier);
    }

    @Override
    public SuperDTO updateSupplier(Long id, SupplierDTO dto) {
        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        existing.setName(dto.getName());
        existing.setContact(dto.getContact());
        existing.setEmail(dto.getEmail());

        Supplier updated = supplierRepository.save(existing);
        return toDTO(updated);
    }

    @Override
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }
}

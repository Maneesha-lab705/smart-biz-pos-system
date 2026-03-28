package com.smartbiz.controller;

import com.smartbiz.dto.SupplierDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ApiResponse<SupplierDTO>> create(@RequestBody SupplierDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Supplier created", (SupplierDTO) supplierService.createSupplier(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplierDTO>>> getAll() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(ApiResponse.success("Suppliers fetched", suppliers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierDTO>> getById(@PathVariable Long id) {
        SupplierDTO supplier = (SupplierDTO) supplierService.getSupplierById(id);
        return ResponseEntity.ok(ApiResponse.success("Supplier found", supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierDTO>> update(@PathVariable Long id, @RequestBody SupplierDTO dto) {
        SupplierDTO updated = (SupplierDTO) supplierService.updateSupplier(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Supplier updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(ApiResponse.success("Supplier deleted", null));
    }
}
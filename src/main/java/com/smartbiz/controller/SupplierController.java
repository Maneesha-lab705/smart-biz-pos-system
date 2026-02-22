package com.smartbiz.controller;

import com.smartbiz.dto.SupplierDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ApiResponse<SupplierDTO>> create(@RequestBody SupplierDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Supplier created", (SupplierDTO) supplierService.createSupplier(dto)));
    }
}
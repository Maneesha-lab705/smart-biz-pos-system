package com.smartbiz.controller;

import com.smartbiz.dto.SaleDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<ApiResponse<SaleDTO>> create(@RequestBody SaleDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Sale created", saleService.createSale(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SaleDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(saleService.getSaleById(id)));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<ApiResponse<List<SaleDTO>>> getByBusiness(@PathVariable Long businessId) {
        return ResponseEntity.ok(ApiResponse.success(saleService.getSalesByBusiness(businessId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok(ApiResponse.success("Sale deleted", null));
    }
}

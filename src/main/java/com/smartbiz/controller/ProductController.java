package com.smartbiz.controller;

import com.smartbiz.dto.ProductDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> create(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Product created", productService.createProduct(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductById(id)));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getByBusiness(@PathVariable Long businessId) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductsByBusiness(businessId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Product updated", productService.updateProduct(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted", null));
    }
}

package com.smartbiz.controller;

import com.smartbiz.dto.CustomerDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private  final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> create(@RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Customer created", customerService.createCustomer(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(customerService.getCustomerById(id)));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getByBusiness(@PathVariable Long businessId) {
        return ResponseEntity.ok(ApiResponse.success(customerService.getCustomersByBusiness(businessId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Customer updated", customerService.updateCustomer(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponse.success("Customer deleted", null));
    }
}

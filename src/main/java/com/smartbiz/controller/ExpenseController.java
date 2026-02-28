package com.smartbiz.controller;

import com.smartbiz.dto.ExpenseDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseDTO>> create(@RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Expense recorded", expenseService.createExpense(dto)));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<ApiResponse<List<ExpenseDTO>>> getByBusiness(@PathVariable Long businessId) {
        return ResponseEntity.ok(ApiResponse.success(expenseService.getExpensesByBusiness(businessId)));
    }

    /**
     * GET /api/expenses/business/{businessId}/range?from=2025-01-01&to=2025-01-31
     */
    @GetMapping("/business/{businessId}/range")
    public ResponseEntity<ApiResponse<List<ExpenseDTO>>> getByDateRange(
            @PathVariable Long businessId,
            @RequestParam String from,
            @RequestParam String to) {
        return ResponseEntity.ok(ApiResponse.success(expenseService.getExpensesByDateRange(businessId, from, to)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseDTO>> update(@PathVariable Long id, @RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Expense updated", expenseService.updateExpense(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok(ApiResponse.success("Expense deleted", null));
    }
}

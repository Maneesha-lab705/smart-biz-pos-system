package com.smartbiz.controller;

import com.smartbiz.dto.AdminStatsDTO;
import com.smartbiz.dto.BusinessDTO;
import com.smartbiz.dto.UserDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    /** GET /api/admin/stats — System-wide statistics */
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<AdminStatsDTO>> getStats() {
        return ResponseEntity.ok(ApiResponse.success(adminService.getSystemStats()));
    }

    /** GET /api/admin/users — All registered users */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success(adminService.getAllUsers()));
    }

    /** GET /api/admin/businesses — All registered businesses */
    @GetMapping("/businesses")
    public ResponseEntity<ApiResponse<List<BusinessDTO>>> getAllBusinesses() {
        return ResponseEntity.ok(ApiResponse.success(adminService.getAllBusinesses()));
    }

    /** PATCH /api/admin/businesses/{id}/status?value=ACTIVE */
    @PatchMapping("/businesses/{id}/status")
    public ResponseEntity<ApiResponse<BusinessDTO>> updateStatus(
            @PathVariable Long id,
            @RequestParam String value) {
        return ResponseEntity.ok(ApiResponse.success("Status updated", adminService.updateBusinessStatus(id, value)));
    }

    /** PATCH /api/admin/businesses/{id}/plan?value=PRO */
    @PatchMapping("/businesses/{id}/plan")
    public ResponseEntity<ApiResponse<BusinessDTO>> updatePlan(
            @PathVariable Long id,
            @RequestParam String value) {
        return ResponseEntity.ok(ApiResponse.success("Plan updated", adminService.updateSubscriptionPlan(id, value)));
    }

    /** DELETE /api/admin/businesses/{id} */
    @DeleteMapping("/businesses/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBusiness(@PathVariable Long id) {
        adminService.deleteBusiness(id);
        return ResponseEntity.ok(ApiResponse.success("Business deleted", null));
    }
}

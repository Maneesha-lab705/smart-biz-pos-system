package com.smartbiz.controller;

import com.smartbiz.dto.BatchDTO;
import com.smartbiz.response.ApiResponse;
import com.smartbiz.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<ApiResponse<BatchDTO>> create(@RequestBody BatchDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Batch created", batchService.createBatch(dto)));
    }
}
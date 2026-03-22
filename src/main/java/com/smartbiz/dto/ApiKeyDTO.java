package com.smartbiz.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiKeyDTO {
    private Long id;
    private String name;
    private String key;
    private LocalDateTime createdAt;
}
package com.smartbiz.mapper;

import com.smartbiz.dto.ApiKeyDTO;
import com.smartbiz.entity.ApiKey;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyMapper {

    public ApiKeyDTO toDTO(ApiKey apiKey) {
        ApiKeyDTO dto = new ApiKeyDTO();
        dto.setId(apiKey.getId());
        dto.setName(apiKey.getName());
        dto.setKey(apiKey.getKey());
        dto.setCreatedAt(apiKey.getCreatedAt());
        return dto;
    }

    public ApiKey toEntity(ApiKeyDTO dto) {
        ApiKey apiKey = new ApiKey();
        apiKey.setId(dto.getId());
        apiKey.setName(dto.getName());
        apiKey.setKey(dto.getKey());
        apiKey.setCreatedAt(dto.getCreatedAt());
        return apiKey;
    }
}
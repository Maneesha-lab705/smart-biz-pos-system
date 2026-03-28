package com.smartbiz.mapper;

import com.smartbiz.dto.BusinessDTO;
import com.smartbiz.entity.Business;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    @Mapping(target = "createdAt", source = "createdAt")
    BusinessDTO toDTO(Business business);

    @Mapping(target = "businessId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "customers", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Business toEntity(BusinessDTO dto);
}

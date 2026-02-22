package com.smartbiz.mapper;

import com.smartbiz.dto.CustomerDTO;
import com.smartbiz.entity.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "business.businessId", target = "businessId")
    @Mapping(source = "business.businessName", target = "businessName")
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "business", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Customer toEntity(CustomerDTO dto);
}

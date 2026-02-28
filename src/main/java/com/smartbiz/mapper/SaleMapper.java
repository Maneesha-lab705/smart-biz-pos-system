package com.smartbiz.mapper;

import com.smartbiz.dto.SaleDTO;
import com.smartbiz.entity.Sale;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {SaleItemMapper.class, PaymentMapper.class})
public interface SaleMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "business.businessId", target = "businessId")
    @Mapping(source = "business.businessName", target = "businessName")
    @Mapping(source = "batch.batchId", target = "batchId")
    @Mapping(source = "saleItems", target = "saleItems")
    @Mapping(source = "payment", target = "payment")
    SaleDTO toDTO(Sale sale);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "business", ignore = true)
    @Mapping(target = "batch", ignore = true)
    @Mapping(target = "saleId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "saleItems", ignore = true)
    @Mapping(target = "payment", ignore = true)
    Sale toEntity(SaleDTO dto);
}

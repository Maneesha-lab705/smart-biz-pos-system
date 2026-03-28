package com.smartbiz.mapper;

import com.smartbiz.dto.SaleItemDTO;
import com.smartbiz.entity.SaleItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SaleItemMapper {
    @Mapping(source = "sale.saleId", target = "saleId")
    @Mapping(source = "sale.invoiceNumber", target = "invoiceNumber")
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "batch.batchId", target = "batchId")
    @Mapping(source = "batch.batchNumber", target = "batchNumber")
    @Mapping(source = "amount", target = "totalAmount")  // ← Add කරන්න
    SaleItemDTO toDTO(SaleItem saleItem);

    @Mapping(target = "sale", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "batch", ignore = true)
    @Mapping(target = "saleItemId", ignore = true)
    @Mapping(source = "totalAmount", target = "amount")  // ← Add කරන්න
    SaleItem toEntity(SaleItemDTO dto);
}

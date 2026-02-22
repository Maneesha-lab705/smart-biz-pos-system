package com.smartbiz.mapper;

import com.smartbiz.dto.BatchDTO;
import com.smartbiz.entity.Batch;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BatchMapper {

    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "supplier.supplierId", target = "supplierId")
    @Mapping(source = "supplier.name", target = "supplierName")
    BatchDTO toDTO(Batch batch);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "batchId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "saleItems", ignore = true)
    @Mapping(target = "sales", ignore = true)
    Batch toEntity(BatchDTO dto);
}

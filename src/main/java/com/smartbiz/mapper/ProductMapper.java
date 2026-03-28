package com.smartbiz.mapper;

import com.smartbiz.dto.ProductDTO;
import com.smartbiz.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    @Mapping(target = "business", ignore = true)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "batches", ignore = true)
    @Mapping(target = "saleItems", ignore = true)
    Product toEntity(ProductDTO dto);
}

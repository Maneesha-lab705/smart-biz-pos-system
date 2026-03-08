package com.smartbiz.mapper;

import com.smartbiz.dto.ProductDTO;
import com.smartbiz.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T18:14:07+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId( product.getProductId() );
        productDTO.setProductName( product.getProductName() );
        productDTO.setStatus( product.getStatus() );
        productDTO.setSellingPrice( product.getSellingPrice() );
        productDTO.setBillingPrice( product.getBillingPrice() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productName( dto.getProductName() );
        product.status( dto.getStatus() );
        product.sellingPrice( dto.getSellingPrice() );
        product.billingPrice( dto.getBillingPrice() );

        return product.build();
    }
}

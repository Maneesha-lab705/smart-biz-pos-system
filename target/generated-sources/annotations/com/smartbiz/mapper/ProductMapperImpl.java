package com.smartbiz.mapper;

import com.smartbiz.dto.ProductDTO;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T21:21:16+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setBusinessId( productBusinessBusinessId( product ) );
        productDTO.setBusinessName( productBusinessBusinessName( product ) );
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

    private Long productBusinessBusinessId(Product product) {
        if ( product == null ) {
            return null;
        }
        Business business = product.getBusiness();
        if ( business == null ) {
            return null;
        }
        Long businessId = business.getBusinessId();
        if ( businessId == null ) {
            return null;
        }
        return businessId;
    }

    private String productBusinessBusinessName(Product product) {
        if ( product == null ) {
            return null;
        }
        Business business = product.getBusiness();
        if ( business == null ) {
            return null;
        }
        String businessName = business.getBusinessName();
        if ( businessName == null ) {
            return null;
        }
        return businessName;
    }
}

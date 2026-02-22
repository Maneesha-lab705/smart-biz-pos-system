package com.smartbiz.mapper;

import com.smartbiz.dto.BatchDTO;
import com.smartbiz.entity.Batch;
import com.smartbiz.entity.Product;
import com.smartbiz.entity.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-21T22:22:08+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BatchMapperImpl implements BatchMapper {

    @Override
    public BatchDTO toDTO(Batch batch) {
        if ( batch == null ) {
            return null;
        }

        BatchDTO batchDTO = new BatchDTO();

        batchDTO.setProductId( batchProductProductId( batch ) );
        batchDTO.setSupplierId( batchSupplierSupplierId( batch ) );
        batchDTO.setSupplierName( batchSupplierName( batch ) );
        batchDTO.setBatchId( batch.getBatchId() );
        batchDTO.setBatchNumber( batch.getBatchNumber() );
        batchDTO.setCategory( batch.getCategory() );
        batchDTO.setQty( batch.getQty() );
        batchDTO.setCostPrice( batch.getCostPrice() );
        batchDTO.setName( batch.getName() );
        batchDTO.setCreatedAt( batch.getCreatedAt() );

        return batchDTO;
    }

    @Override
    public Batch toEntity(BatchDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Batch.BatchBuilder batch = Batch.builder();

        batch.batchNumber( dto.getBatchNumber() );
        batch.category( dto.getCategory() );
        batch.qty( dto.getQty() );
        batch.costPrice( dto.getCostPrice() );
        batch.name( dto.getName() );

        return batch.build();
    }

    private Long batchProductProductId(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        Product product = batch.getProduct();
        if ( product == null ) {
            return null;
        }
        Long productId = product.getProductId();
        if ( productId == null ) {
            return null;
        }
        return productId;
    }

    private Long batchSupplierSupplierId(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        Supplier supplier = batch.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        Long supplierId = supplier.getSupplierId();
        if ( supplierId == null ) {
            return null;
        }
        return supplierId;
    }

    private String batchSupplierName(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        Supplier supplier = batch.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String name = supplier.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

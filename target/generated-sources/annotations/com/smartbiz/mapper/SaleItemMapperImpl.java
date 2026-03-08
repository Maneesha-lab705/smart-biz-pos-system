package com.smartbiz.mapper;

import com.smartbiz.dto.SaleItemDTO;
import com.smartbiz.entity.Batch;
import com.smartbiz.entity.Product;
import com.smartbiz.entity.Sale;
import com.smartbiz.entity.SaleItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T18:14:07+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class SaleItemMapperImpl implements SaleItemMapper {

    @Override
    public SaleItemDTO toDTO(SaleItem saleItem) {
        if ( saleItem == null ) {
            return null;
        }

        SaleItemDTO saleItemDTO = new SaleItemDTO();

        saleItemDTO.setSaleId( saleItemSaleSaleId( saleItem ) );
        saleItemDTO.setInvoiceNumber( saleItemSaleInvoiceNumber( saleItem ) );
        saleItemDTO.setProductId( saleItemProductProductId( saleItem ) );
        saleItemDTO.setBatchId( saleItemBatchBatchId( saleItem ) );
        saleItemDTO.setBatchNumber( saleItemBatchBatchNumber( saleItem ) );
        saleItemDTO.setTotalAmount( saleItem.getAmount() );
        saleItemDTO.setSaleItemId( saleItem.getSaleItemId() );
        saleItemDTO.setUnitPrice( saleItem.getUnitPrice() );
        saleItemDTO.setQty( saleItem.getQty() );

        return saleItemDTO;
    }

    @Override
    public SaleItem toEntity(SaleItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SaleItem.SaleItemBuilder saleItem = SaleItem.builder();

        saleItem.amount( dto.getTotalAmount() );
        saleItem.qty( dto.getQty() );
        saleItem.unitPrice( dto.getUnitPrice() );

        return saleItem.build();
    }

    private Long saleItemSaleSaleId(SaleItem saleItem) {
        if ( saleItem == null ) {
            return null;
        }
        Sale sale = saleItem.getSale();
        if ( sale == null ) {
            return null;
        }
        Long saleId = sale.getSaleId();
        if ( saleId == null ) {
            return null;
        }
        return saleId;
    }

    private String saleItemSaleInvoiceNumber(SaleItem saleItem) {
        if ( saleItem == null ) {
            return null;
        }
        Sale sale = saleItem.getSale();
        if ( sale == null ) {
            return null;
        }
        String invoiceNumber = sale.getInvoiceNumber();
        if ( invoiceNumber == null ) {
            return null;
        }
        return invoiceNumber;
    }

    private Long saleItemProductProductId(SaleItem saleItem) {
        if ( saleItem == null ) {
            return null;
        }
        Product product = saleItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long productId = product.getProductId();
        if ( productId == null ) {
            return null;
        }
        return productId;
    }

    private Long saleItemBatchBatchId(SaleItem saleItem) {
        if ( saleItem == null ) {
            return null;
        }
        Batch batch = saleItem.getBatch();
        if ( batch == null ) {
            return null;
        }
        Long batchId = batch.getBatchId();
        if ( batchId == null ) {
            return null;
        }
        return batchId;
    }

    private String saleItemBatchBatchNumber(SaleItem saleItem) {
        if ( saleItem == null ) {
            return null;
        }
        Batch batch = saleItem.getBatch();
        if ( batch == null ) {
            return null;
        }
        String batchNumber = batch.getBatchNumber();
        if ( batchNumber == null ) {
            return null;
        }
        return batchNumber;
    }
}

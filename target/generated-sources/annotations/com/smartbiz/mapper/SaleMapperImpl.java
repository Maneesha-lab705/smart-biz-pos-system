package com.smartbiz.mapper;

import com.smartbiz.dto.SaleDTO;
import com.smartbiz.dto.SaleItemDTO;
import com.smartbiz.entity.Batch;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Customer;
import com.smartbiz.entity.Sale;
import com.smartbiz.entity.SaleItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T21:21:17+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Autowired
    private SaleItemMapper saleItemMapper;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public SaleDTO toDTO(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleDTO saleDTO = new SaleDTO();

        saleDTO.setCustomerId( saleCustomerCustomerId( sale ) );
        saleDTO.setCustomerName( saleCustomerName( sale ) );
        saleDTO.setBusinessId( saleBusinessBusinessId( sale ) );
        saleDTO.setBusinessName( saleBusinessBusinessName( sale ) );
        saleDTO.setBatchId( saleBatchBatchId( sale ) );
        saleDTO.setSaleItems( saleItemListToSaleItemDTOList( sale.getSaleItems() ) );
        saleDTO.setPayment( paymentMapper.toDTO( sale.getPayment() ) );
        saleDTO.setSaleId( sale.getSaleId() );
        saleDTO.setInvoiceNumber( sale.getInvoiceNumber() );
        saleDTO.setCreatedAt( sale.getCreatedAt() );

        return saleDTO;
    }

    @Override
    public Sale toEntity(SaleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Sale.SaleBuilder sale = Sale.builder();

        sale.invoiceNumber( dto.getInvoiceNumber() );

        return sale.build();
    }

    private Long saleCustomerCustomerId(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Customer customer = sale.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long customerId = customer.getCustomerId();
        if ( customerId == null ) {
            return null;
        }
        return customerId;
    }

    private String saleCustomerName(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Customer customer = sale.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String name = customer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long saleBusinessBusinessId(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Business business = sale.getBusiness();
        if ( business == null ) {
            return null;
        }
        Long businessId = business.getBusinessId();
        if ( businessId == null ) {
            return null;
        }
        return businessId;
    }

    private String saleBusinessBusinessName(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Business business = sale.getBusiness();
        if ( business == null ) {
            return null;
        }
        String businessName = business.getBusinessName();
        if ( businessName == null ) {
            return null;
        }
        return businessName;
    }

    private Long saleBatchBatchId(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Batch batch = sale.getBatch();
        if ( batch == null ) {
            return null;
        }
        Long batchId = batch.getBatchId();
        if ( batchId == null ) {
            return null;
        }
        return batchId;
    }

    protected List<SaleItemDTO> saleItemListToSaleItemDTOList(List<SaleItem> list) {
        if ( list == null ) {
            return null;
        }

        List<SaleItemDTO> list1 = new ArrayList<SaleItemDTO>( list.size() );
        for ( SaleItem saleItem : list ) {
            list1.add( saleItemMapper.toDTO( saleItem ) );
        }

        return list1;
    }
}

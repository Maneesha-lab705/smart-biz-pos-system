package com.smartbiz.mapper;

import com.smartbiz.dto.PaymentDTO;
import com.smartbiz.entity.Payment;
import com.smartbiz.entity.Sale;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T18:14:07+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDTO toDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setSaleId( paymentSaleSaleId( payment ) );
        paymentDTO.setInvoiceNumber( paymentSaleInvoiceNumber( payment ) );
        paymentDTO.setPaymentId( payment.getPaymentId() );
        paymentDTO.setPaidAt( payment.getPaidAt() );
        paymentDTO.setPaymentStatus( payment.getPaymentStatus() );
        paymentDTO.setPaymentMethod( payment.getPaymentMethod() );
        if ( payment.getAmount() != null ) {
            paymentDTO.setAmount( payment.getAmount().doubleValue() );
        }

        return paymentDTO;
    }

    @Override
    public Payment toEntity(PaymentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.paymentStatus( dto.getPaymentStatus() );
        payment.paymentMethod( dto.getPaymentMethod() );
        if ( dto.getAmount() != null ) {
            payment.amount( BigDecimal.valueOf( dto.getAmount() ) );
        }

        return payment.build();
    }

    private Long paymentSaleSaleId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Sale sale = payment.getSale();
        if ( sale == null ) {
            return null;
        }
        Long saleId = sale.getSaleId();
        if ( saleId == null ) {
            return null;
        }
        return saleId;
    }

    private String paymentSaleInvoiceNumber(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Sale sale = payment.getSale();
        if ( sale == null ) {
            return null;
        }
        String invoiceNumber = sale.getInvoiceNumber();
        if ( invoiceNumber == null ) {
            return null;
        }
        return invoiceNumber;
    }
}

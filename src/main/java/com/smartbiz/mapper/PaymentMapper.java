package com.smartbiz.mapper;

import com.smartbiz.dto.PaymentDTO;
import com.smartbiz.entity.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "sale.saleId", target = "saleId")
    @Mapping(source = "sale.invoiceNumber", target = "invoiceNumber")
    PaymentDTO toDTO(Payment payment);

    @Mapping(target = "sale", ignore = true)
    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "paidAt", ignore = true)
    Payment toEntity(PaymentDTO dto);
}

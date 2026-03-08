package com.smartbiz.service.impl;

import com.smartbiz.dto.PaymentDTO;
import com.smartbiz.dto.SaleDTO;
import com.smartbiz.dto.SaleItemDTO;
import com.smartbiz.entity.*;
import com.smartbiz.exception.ResourceNotFoundException;
import com.smartbiz.mapper.SaleMapper;
import com.smartbiz.repository.*;
import com.smartbiz.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;
    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;  // ← Add කළා
    private final SaleMapper saleMapper;

    @Override
    @Transactional
    public SaleDTO createSale(SaleDTO dto) {
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Business not found: " + dto.getBusinessId()));

        Sale sale = new Sale();
        sale.setBusiness(business);
        sale.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        sale.setCreatedAt(new Date());

        if (dto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + dto.getCustomerId()));
            sale.setCustomer(customer);
        }

        if (dto.getBatchId() != null) {
            Batch batch = batchRepository.findById(dto.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Batch not found: " + dto.getBatchId()));
            sale.setBatch(batch);
        }

        Sale savedSale = saleRepository.save(sale);

        // Save sale items
        List<SaleItem> items = new ArrayList<>();
        if (dto.getSaleItems() != null) {
            for (SaleItemDTO itemDTO : dto.getSaleItems()) {
                SaleItem item = new SaleItem();
                item.setSale(savedSale);
                item.setQty(itemDTO.getQty());
                item.setAmount(itemDTO.getTotalAmount());
                item.setUnitPrice(itemDTO.getUnitPrice());


                Product product = productRepository.findById(itemDTO.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + itemDTO.getProductId()));

                // ✅ Stock qty අඩු කරන්න
                int currentQty = product.getTotalQty() != null ? product.getTotalQty() : 0;
                int soldQty = itemDTO.getQty() != null ? itemDTO.getQty() : 0;

                if (currentQty < soldQty) {
                    throw new RuntimeException("Insufficient stock for product: " + product.getProductName()
                            + " | Available: " + currentQty + " | Requested: " + soldQty);
                }

                product.setTotalQty(currentQty - soldQty);
                productRepository.save(product);

                item.setCostPrice(product.getBillingPrice());
                item.setProduct(product);

                if (itemDTO.getBatchId() != null) {
                    Batch batch = batchRepository.findById(itemDTO.getBatchId())
                            .orElseThrow(() -> new ResourceNotFoundException("Batch not found: " + itemDTO.getBatchId()));
                    item.setBatch(batch);
                }
                items.add(saleItemRepository.save(item));
            }
        }
        savedSale.setSaleItems(items);

        // Payment save කරන්න
        if (dto.getPayment() != null) {
            PaymentDTO paymentDTO = dto.getPayment();
            Payment payment = new Payment();
            payment.setSale(savedSale);
            payment.setPaidAt(new Date());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus() != null
                    ? paymentDTO.getPaymentStatus() : "COMPLETED");
            payment.setAmount(paymentDTO.getAmount() != null
                    ? BigDecimal.valueOf(paymentDTO.getAmount()) : BigDecimal.ZERO);
            paymentRepository.save(payment);
        }

        return saleMapper.toDTO(savedSale);
    }

    @Override
    public SaleDTO getSaleById(Long saleId) {
        return saleMapper.toDTO(saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found: " + saleId)));
    }

    @Override
    public List<SaleDTO> getSalesByBusiness(Long businessId) {
        return saleRepository.findAllByBusinessBusinessId(businessId)
                .stream().map(saleMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteSale(Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new ResourceNotFoundException("Sale not found: " + saleId);
        }
        saleRepository.deleteById(saleId);
    }
}
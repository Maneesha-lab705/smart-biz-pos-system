package com.smartbiz.service;

import com.smartbiz.dto.SaleDTO;
import java.util.List;

public interface SaleService {
    SaleDTO createSale(SaleDTO dto);
    SaleDTO getSaleById(Long saleId);
    List<SaleDTO> getSalesByBusiness(Long businessId);
    void deleteSale(Long saleId);
}

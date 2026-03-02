package com.smartbiz.service;

import com.smartbiz.dto.SuperDTO;
import com.smartbiz.dto.SupplierDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SupplierService {
    Object createSupplier(SupplierDTO dto);
    List<SupplierDTO> getAllSuppliers();
    SuperDTO getSupplierById(Long id);
    SuperDTO updateSupplier(Long id, SupplierDTO dto);
    void deleteSupplier(Long id);
}

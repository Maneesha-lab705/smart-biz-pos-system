package com.smartbiz.service;

import com.smartbiz.dto.SupplierDTO;
import org.springframework.stereotype.Service;

public interface SupplierService {
    Object createSupplier(SupplierDTO dto);
}

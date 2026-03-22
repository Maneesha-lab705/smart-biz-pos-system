package com.smartbiz.service;

import com.smartbiz.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO dto);
    CustomerDTO getCustomerById(Long customerId);
    List<CustomerDTO> getCustomersByBusiness(Long businessId);
    CustomerDTO updateCustomer(Long customerId, CustomerDTO dto);
    void deleteCustomer(Long customerId);
}

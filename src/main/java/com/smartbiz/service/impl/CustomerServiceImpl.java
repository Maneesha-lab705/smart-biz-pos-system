package com.smartbiz.service.impl;

import com.smartbiz.dto.CustomerDTO;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Customer;
import com.smartbiz.exception.BusinessException;
import com.smartbiz.exception.ResourceNotFoundException;
import com.smartbiz.mapper.CustomerMapper;
import com.smartbiz.repository.BusinessRepository;
import com.smartbiz.repository.CustomerRepository;
import com.smartbiz.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
        if (customerRepository.existsByEmailAndBusinessBusinessId(dto.getEmail(), dto.getBusinessId())) {
            throw new BusinessException("Customer with this email already exists in your business");
        }
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Business not found: " + dto.getBusinessId()));

        Customer customer = customerMapper.toEntity(dto);
        customer.setBusiness(business);
        return customerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        return customerMapper.toDTO(customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId)));
    }

    @Override
    public List<CustomerDTO> getCustomersByBusiness(Long businessId) {
        return customerRepository.findAllByBusinessBusinessId(businessId)
                .stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO dto) {
        Customer existing = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        return customerMapper.toDTO(customerRepository.save(existing));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found: " + customerId);
        }
        customerRepository.deleteById(customerId);
    }
}

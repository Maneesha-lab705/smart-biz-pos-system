package com.smartbiz.service.impl;

import com.smartbiz.dto.ProductDTO;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Product;
import com.smartbiz.exception.ResourceNotFoundException;
import com.smartbiz.mapper.ProductMapper;
import com.smartbiz.repository.BusinessRepository;
import com.smartbiz.repository.ProductRepository;
import com.smartbiz.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BusinessRepository businessRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Business not found: " + dto.getBusinessId()));
        Product product = productMapper.toEntity(dto);
        product.setBusiness(business);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        return productMapper.toDTO(productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId)));
    }

    @Override
    public List<ProductDTO> getProductsByBusiness(Long businessId) {
        return productRepository.findAllByBusinessBusinessId(businessId)
                .stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO dto) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));
        existing.setStatus(dto.getStatus());
        existing.setSellingPrice(dto.getSellingPrice());
        existing.setBillingPrice(dto.getBillingPrice());
        return productMapper.toDTO(productRepository.save(existing));
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found: " + productId);
        }
        productRepository.deleteById(productId);
    }
}

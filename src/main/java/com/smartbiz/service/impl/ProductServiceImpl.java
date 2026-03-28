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
        List<Product> products = productRepository.findAllByBusinessBusinessId(businessId);
        products.forEach(p -> System.out.println(
                "Product: " + p.getProductId() +
                        " | Name: " + p.getProductName() +
                        " | TotalQty: " + p.getTotalQty()
        ));
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setStatus(product.getStatus());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setBillingPrice(product.getBillingPrice());
        dto.setQty(product.getTotalQty() != null ? product.getTotalQty() : 0);

        if (product.getBusiness() != null) {
            dto.setBusinessId(product.getBusiness().getBusinessId());
            dto.setBusinessName(product.getBusiness().getBusinessName());
        }

        return dto;
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

package com.smartbiz.service;

import com.smartbiz.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO dto);
    ProductDTO getProductById(Long productId);
    List<ProductDTO> getProductsByBusiness(Long businessId);
    ProductDTO updateProduct(Long productId, ProductDTO dto);
    void deleteProduct(Long productId);
}

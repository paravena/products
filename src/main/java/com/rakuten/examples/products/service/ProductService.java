package com.rakuten.examples.products.service;

import com.rakuten.examples.products.util.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProducts();
    ProductDTO createNewProduct(ProductDTO productDTO);
    ProductDTO findProductById(Long id);
}

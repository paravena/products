package com.rakuten.examples.products.service;

import com.rakuten.examples.products.domain.Product;
import com.rakuten.examples.products.repository.ProductRepository;
import com.rakuten.examples.products.util.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll()
                .stream().map(ProductDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public ProductDTO createNewProduct(ProductDTO productDTO) {
        Product product = productRepository.save(productDTO.toProduct());
        return ProductDTO.valueOf(product);
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found for id " + id);
        }
        return ProductDTO.valueOf(product);
    }
}

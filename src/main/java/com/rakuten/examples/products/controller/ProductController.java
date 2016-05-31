package com.rakuten.examples.products.controller;

import com.rakuten.examples.products.service.ProductService;
import com.rakuten.examples.products.util.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findAllProducts() {
        return productService.findAllProducts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductDTO> createNewProduct(@RequestBody ProductDTO productDTO,
                                                       UriComponentsBuilder ucb) {
        ProductDTO newProductDTO = productService.createNewProduct(productDTO);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb
                .path("/product/")
                .path(String.valueOf(newProductDTO.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);
        return new ResponseEntity<>(newProductDTO,
                headers,
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }
}

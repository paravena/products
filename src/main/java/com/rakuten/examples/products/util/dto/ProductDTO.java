package com.rakuten.examples.products.util.dto;

import com.rakuten.examples.products.domain.Product;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Long price;

    public ProductDTO() {
    }

    public ProductDTO(Long id,
                      String name,
                      String description,
                      Long price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Product toProduct() {
        return new Product(this.getName(),
                this.getDescription(),
                this.getPrice());
    }

    public static ProductDTO valueOf(Product product) {
        return new ProductDTO(product);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

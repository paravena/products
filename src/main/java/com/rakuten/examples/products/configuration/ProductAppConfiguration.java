package com.rakuten.examples.products.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages="com.rakuten.examples.products.repository")
public class ProductAppConfiguration {
}

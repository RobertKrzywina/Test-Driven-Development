package pl.robert.project.product.domain;

import org.springframework.context.annotation.Configuration;

@Configuration
class ProductConfiguration {

    ProductFacade facade() {
        InMemoryProductRepository productRepository = new InMemoryProductRepository();
        return new ProductFacade(productRepository);
    }
}

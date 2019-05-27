package pl.robert.project.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductConfiguration {

    ProductFacade facade() {
        return new ProductFacade(new InMemoryProductRepository());
    }

    @Bean
    ProductFacade facade(ProductRepository repository) {
        return new ProductFacade(repository);
    }
}

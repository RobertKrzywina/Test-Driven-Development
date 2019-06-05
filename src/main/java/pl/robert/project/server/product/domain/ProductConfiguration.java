package pl.robert.project.server.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductConfiguration {

    ProductFacade facade() {

        InMemoryProductRepository repository = new InMemoryProductRepository();

        return new ProductFacade(repository,
                                 new ProductValidator(repository));
    }

    @Bean
    ProductFacade facade(ProductRepository repository) {
        return new ProductFacade(repository,
                                 new ProductValidator(repository));
    }
}

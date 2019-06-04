package pl.robert.project;

import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.robert.project.server.product.domain.ProductFacade;
import pl.robert.project.server.product.domain.dto.CreateProductDto;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProjectApplication.class);

        ProductFacade facade = context.getBean(ProductFacade.class);

        Stream.of(
                new CreateProductDto(1L, "Apple"),
                new CreateProductDto(2L, "Banana"),
                new CreateProductDto(3L, "Fruit"),
                new CreateProductDto(4L, "Orange"),
                new CreateProductDto(5L, "Grape"),
                new CreateProductDto(6L, "Lemon"),
                new CreateProductDto(7L, "Mango"),
                new CreateProductDto(8L, "Pear"),
                new CreateProductDto(9L, "Coconut"),
                new CreateProductDto(10L, "Plum"),
                new CreateProductDto(11L, "Watermelon")
        ).forEach(facade::create);
    }
}

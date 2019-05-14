package pl.robert.project.product.domain;

import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.robert.project.product.domain.dto.ProductDto;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
class Product {

    long id;
    String name;

    ProductDto dto() {
        return ProductDto.builder()
                .id(id)
                .name(name)
                .build();
    }
}

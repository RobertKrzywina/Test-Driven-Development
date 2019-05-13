package pl.robert.project.product.domain;

import pl.robert.project.product.domain.dto.CreateProductDto;

class ProductFactory {

    static Product create(CreateProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}

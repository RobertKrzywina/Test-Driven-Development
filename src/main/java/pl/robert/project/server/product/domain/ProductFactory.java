package pl.robert.project.server.product.domain;

import pl.robert.project.server.product.domain.dto.CreateProductDto;

class ProductFactory {

    static Product create(CreateProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}

package pl.robert.project.product.domain.dto;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class ProductDto {

    Long id;
    String name;
}

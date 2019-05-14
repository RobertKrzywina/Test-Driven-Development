package pl.robert.project.product.domain.dto;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class ProductDto {

    long id;
    String name;
}

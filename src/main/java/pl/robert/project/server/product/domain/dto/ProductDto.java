package pl.robert.project.server.product.domain.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class ProductDto {

    Long id;
    String name;
}

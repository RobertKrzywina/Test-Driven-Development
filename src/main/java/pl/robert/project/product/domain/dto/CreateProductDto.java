package pl.robert.project.product.domain.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductDto {

    long id;
    String name;
}

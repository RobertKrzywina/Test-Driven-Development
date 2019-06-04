package pl.robert.project.server.product.domain.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductDto {

    Long id;
    String name;

    public CreateProductDto(String name) {
        this.name = name;
    }
}

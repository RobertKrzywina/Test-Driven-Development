package pl.robert.project.server.product.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import pl.robert.project.server.product.domain.dto.ProductDto;

import static pl.robert.project.server.shared.Constants.Product.COL_LENGTH_NAME;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = COL_LENGTH_NAME, nullable = false)
    String name;

    ProductDto dto() {
        return ProductDto.builder()
                .id(id)
                .name(name)
                .build();
    }
}

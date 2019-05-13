package pl.robert.project.product.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
class Product {

    long id;
    String name;
}

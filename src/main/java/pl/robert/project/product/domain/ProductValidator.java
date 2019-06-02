package pl.robert.project.product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import static pl.robert.project.shared.Constants.Product.COL_LENGTH_NAME;
import static pl.robert.project.shared.Constants.Product.PRODUCT_NAME_FORMAT_REGEX;

import pl.robert.project.product.domain.exception.InvalidProductException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class ProductValidator {

    void checkInputData(final String name) {
        boolean isInvalid = false;

        if (name == null) {
            isInvalid = true;
        } else if (PRODUCT_NAME_FORMAT_REGEX.matcher(name).find()) {
            isInvalid = true;
        } else if (name.isBlank()) {
            isInvalid = true;
        } else if (name.length() > COL_LENGTH_NAME) {
            isInvalid = true;
        }

        if (isInvalid) {
            throw new InvalidProductException(name);
        }
    }
}

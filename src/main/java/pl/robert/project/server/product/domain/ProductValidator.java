package pl.robert.project.server.product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import static pl.robert.project.server.shared.Constants.Product.COL_LENGTH_NAME;
import static pl.robert.project.server.shared.Constants.Product.PRODUCT_NAME_FORMAT_REGEX;

import pl.robert.project.server.product.domain.exception.InvalidProductException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class ProductValidator {

    ProductRepository repository;

    void checkInputData(final String name) {
        InvalidProductException.CAUSE cause = null;

        if (name == null) {
            cause = InvalidProductException.CAUSE.NULL;
        } else if (!PRODUCT_NAME_FORMAT_REGEX.matcher(name).find()) {
            cause = InvalidProductException.CAUSE.FORMAT;
        } else if (name.isBlank()) {
            cause = InvalidProductException.CAUSE.BLANK;
        } else if (name.length() > COL_LENGTH_NAME) {
            cause = InvalidProductException.CAUSE.LENGTH;
        } else if (isNameUnique(name)) {
            cause = InvalidProductException.CAUSE.UNIQUE;
        }

        if (cause != null) {
            throw new InvalidProductException(cause);
        }
    }

    private boolean isNameUnique(String name) {
        return repository.findAll()
                .stream()
                .anyMatch(product -> product.getName().equals(name));
    }
}

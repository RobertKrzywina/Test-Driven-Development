package pl.robert.project.product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.project.product.domain.exception.InvalidProductException;

import java.util.regex.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class ProductValidator {

    void checkInputData(String name) {
        boolean isInvalid = false;

        if (name == null) {
            isInvalid = true;
        } else if (Pattern.compile("^-?\\d+$", Pattern.CASE_INSENSITIVE).matcher(name).find()) {
            isInvalid = true;
        } else if (name.isEmpty() || name.isBlank()) {
            isInvalid = true;
        } else if (name.length() > 35) {
            isInvalid = true;
        }

        if (isInvalid) {
            throw new InvalidProductException(name);
        }
    }
}

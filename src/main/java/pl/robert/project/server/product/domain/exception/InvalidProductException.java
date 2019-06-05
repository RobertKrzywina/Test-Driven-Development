package pl.robert.project.server.product.domain.exception;

import lombok.Getter;
import lombok.AllArgsConstructor;

import pl.robert.project.server.shared.ParameterizedException;

public class InvalidProductException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        NOT_FOUND("No product found of id "),
        NULL("Enter name of a product"),
        FORMAT("Wrong format of a product name"),
        BLANK("Name of a product is required"),
        LENGTH("The maximum length of a product name is 35 chars"),
        UNIQUE("Name of a product should be unique");

        String message;
    }

    public InvalidProductException(CAUSE cause) {
        super(cause.message);
    }
}

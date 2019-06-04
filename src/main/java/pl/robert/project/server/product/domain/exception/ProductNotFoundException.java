package pl.robert.project.server.product.domain.exception;

import pl.robert.project.server.shared.ParameterizedException;

public class ProductNotFoundException extends ParameterizedException {

    public ProductNotFoundException(Long id) {
        super(InvalidProductException.CAUSE.NOT_FOUND.message + id);
    }
}

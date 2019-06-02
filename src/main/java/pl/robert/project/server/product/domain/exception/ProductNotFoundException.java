package pl.robert.project.server.product.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("No product of id \"" + id + "\" found", null, false, false);
    }
}

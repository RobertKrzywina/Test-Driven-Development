package pl.robert.project.product.domain.exception;

public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String cause) {
        super("Invalid product exception cause \"" + cause + "\"", null, false, false);
    }
}

package pl.robert.project.server.shared;

public abstract class ParameterizedException extends RuntimeException {

    public static String label;

    public ParameterizedException(String message) {
        super(message);
        label = message;
    }
}

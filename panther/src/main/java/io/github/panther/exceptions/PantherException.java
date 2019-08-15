package io.github.panther.exceptions;

public class PantherException extends RuntimeException {

    /**
     * Creates a new instance with {@code null} as its detail message.
     */
    protected PantherException() {
    }

    /**
     * Creates a new instance with the given detail message.
     */
    protected PantherException(String message) {
        super(message);
    }

    /**
     * Creates a new instance with the given detail message and cause.
     */
    public PantherException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance with the given cause.
     */
    public PantherException(Throwable cause) {
        super(cause);
    }

    private static final long serialVersionUID = 0;

}

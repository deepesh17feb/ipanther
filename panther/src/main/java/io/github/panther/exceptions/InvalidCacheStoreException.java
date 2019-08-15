package io.github.panther.exceptions;


public class InvalidCacheStoreException extends Exception {

    /**
     * Creates a new instance with {@code null} as its detail message.
     */
    public InvalidCacheStoreException() {
    }

    /**
     * Creates a new instance with the given detail message.
     */
    public InvalidCacheStoreException(String message) {
        super(message);
    }

    /**
     * Creates a new instance with the given detail message and cause.
     */
    public InvalidCacheStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance with the given cause.
     */
    public InvalidCacheStoreException(Throwable cause) {
        super(cause);
    }

    private static final long serialVersionUID = 0;

}

package doiframework.errors;

import doiframework.core.resource.DataSource;

/**
 * Thrown whenever the framework can't find a appropriate constructor
 * from {@link DataSource} resource.
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public final class NoSuchConstructorError extends Error {
    private static final String BASE_MSG = "Could not find proper constructor";
    public NoSuchConstructorError() {
        this(BASE_MSG);
    }

    /**
     * @param msg String
     */
    private NoSuchConstructorError(String msg) {
        super(BASE_MSG + " " + msg);
    }
}

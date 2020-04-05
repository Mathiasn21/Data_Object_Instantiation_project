package framework.errors;

import framework.resource.Resource;

/**
 * Thrown whenver the framework can't find a apropriate constructor
 * from {@link Resource} resource.
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

package framework.errors;

/**
 * Thrown whenver the framework can't find a apropriate constructor
 * from {@link framework.utilities.data.Resource} data.
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public final class NoSuchConstructor extends Error {
    private static final String BASE_MSG = "Could not find proper constructor";
    public NoSuchConstructor() {
        this(BASE_MSG);
    }

    /**
     * @param msg String
     */
    private NoSuchConstructor(String msg) {
        super(BASE_MSG + " " + msg);
    }
}

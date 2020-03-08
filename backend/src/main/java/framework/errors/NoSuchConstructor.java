package framework.errors;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
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

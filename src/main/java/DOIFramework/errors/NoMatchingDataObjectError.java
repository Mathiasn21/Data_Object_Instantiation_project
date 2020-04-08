package DOIFramework.errors;

/**
 * Thrown whenever the framework does not find a suitable {@link DOIFramework.core.annotations.DataObject}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public final class NoMatchingDataObjectError extends Error {
    private static final String BASE_MSG = "Could not find proper DataObject.";
    public NoMatchingDataObjectError() { this(BASE_MSG); }

    /**
     * @param msg String
     */
    private NoMatchingDataObjectError(String msg) { super(BASE_MSG + " " + msg); }
}

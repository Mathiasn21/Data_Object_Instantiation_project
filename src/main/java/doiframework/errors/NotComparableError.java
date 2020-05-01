package doiframework.errors;

/**
 * Error thrown whenever a {@link doiframework.core.annotations.DataObject} is not
 * {@link Comparable}
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class NotComparableError extends Error{
    public NotComparableError() { super(); }
    public NotComparableError(String message) { super(message); }
}

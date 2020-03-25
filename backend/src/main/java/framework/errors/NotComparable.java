package framework.errors;

/**
 * Error thrown whenever a {@link framework.annotations.DataObject} is not
 * {@link Comparable}
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class NotComparable extends Error{
    public NotComparable() { super(); }
    public NotComparable(String message) { super(message); }
}

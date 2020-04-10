package DOIFramework.exceptions;

/**
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 */
public class NotPrimitiveNumber extends Exception{
    public NotPrimitiveNumber() {
        super();
    }

    public NotPrimitiveNumber(String message) {
        super(message);
    }
}
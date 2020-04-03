package framework.exceptions;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class NoExistingColumn extends Exception{
    public NoExistingColumn() {
        super("There is no existing column by that name");
    }
}


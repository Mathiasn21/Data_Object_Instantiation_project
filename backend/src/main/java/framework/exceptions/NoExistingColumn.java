package framework.exceptions;

/**
 * @author Maria Pedersen
 */
public class NoExistingColumn extends Exception{
    public NoExistingColumn() {
        super("There is no existing column by that name");
    }
}


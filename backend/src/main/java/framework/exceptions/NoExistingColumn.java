package framework.exceptions;

public class NoExistingColumn extends Exception{
    public NoExistingColumn() {
        super("There are no existing column by that name");
    }
}


package framework.exceptions;

public class NoExistingColumn extends Exception{
    public NoExistingColumn() {
        super("There is no existing column by that name");
    }
}


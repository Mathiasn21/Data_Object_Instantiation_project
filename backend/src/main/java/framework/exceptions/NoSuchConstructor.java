package framework.exceptions;

public class NoSuchConstructor extends Error {
    private static final String BASE_MSG = "Could not find proper constructor";
    public NoSuchConstructor() {
        this(BASE_MSG);
    }

    private NoSuchConstructor(String msg) {
        super(BASE_MSG + " " + msg);
    }
}

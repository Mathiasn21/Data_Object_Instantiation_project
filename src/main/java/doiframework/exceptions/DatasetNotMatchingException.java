package doiframework.exceptions;

public final class DatasetNotMatchingException extends Exception{
        public DatasetNotMatchingException(){ super("Exception: Datasets must be the same size");
        }
}

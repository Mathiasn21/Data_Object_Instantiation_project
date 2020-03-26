package framework.annotations;

import framework.utilities.data.handle.CSVHandler;

public @interface CSV {
    String[] sources();
    Class<? extends CSVHandler> handler() default CSVHandler.class;
}

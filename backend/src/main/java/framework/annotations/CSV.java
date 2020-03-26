package framework.annotations;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;

public @interface CSV {
    String[] sources();
    Class<? extends Resource> sourceClass() default Resource.class;
    Class<? extends CSVHandler> handler() default CSVHandler.class;
}

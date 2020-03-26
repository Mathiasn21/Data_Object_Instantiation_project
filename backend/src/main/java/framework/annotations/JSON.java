package framework.annotations;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;

public @interface JSON {
    String[] sources();
    Class<? extends Resource> sourceClass() default Resource.class;
    Class<? extends JSONHandler> handler() default JSONHandler.class;
}

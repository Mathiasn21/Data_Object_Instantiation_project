package framework.annotations;

import framework.utilities.data.Resource;

public @interface JSON {
    String[] sources();

    Class<? extends Resource> sourceClass();
}

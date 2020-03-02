package coll.test;

import framework.annotations.DataObject;

public @interface Collector {
    String file() default "";
    String fileType() default "";

    Class<? extends DataObject> dataClass();

    String fileName() default "";
}

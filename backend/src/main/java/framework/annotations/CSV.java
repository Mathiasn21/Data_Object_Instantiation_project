package framework.annotations;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CSV {
    String[] sources();
    Class<? extends Resource> sourceClass() default Resource.class;//TODO: Might be deleted?
    Class<? extends CSVHandler> handler() default CSVHandler.class;
}
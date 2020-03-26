package framework.annotations;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JSON {
    String[] sources();
    Class<? extends Resource> sourceClass() default Resource.class;
    Class<? extends JSONHandler> handler() default JSONHandler.class;
}

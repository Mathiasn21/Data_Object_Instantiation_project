package doiframework.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for mapping specific columns execute dataset to
 * class fields. If no column name is specified it will utilize the name gotten execute class field
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataField {
    String primaryColumnName() default "";
}

package framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for specifying a class representing a given dataset.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataObject {
    String fileName() default "";

    /**
     * PrimaryKeys are keys that define data columns from data set
     * @return String[]
     */
    String[] primaryKeys() default "";

    /**
     * Defines names for the informationalRows
     * @return String[]
     */
    String[] informationalRows() default "";

    /**
     * Returns the primitive types describing the data type
     * that corresponds the primary keys form a dataset
     * @return Class[]
     */
    Class<?>[] primitiveTypes() default void.class;
}

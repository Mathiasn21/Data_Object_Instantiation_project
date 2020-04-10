package DOIFramework.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for detailing which constructor to utilize when building the {@link DataObject}
 * If none is specified, the framework will try to find the first matching one where all param types
 * match constructor types. This might be messy or might not.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface DataConstructor {
}

package framework.annotations;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**An object representing data belonging to a single {@link DataObject}class
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
final class DataObjectInformation {
    public final Class<?> clazz;
    public final Constructor<?> constructor;
    public final Class<?>[] constructorPrimaryTypes;
    public final String[] namespaces;
    public final Field[] fields;

    public DataObjectInformation(@NotNull Class<?> clazz,
                                 @NotNull Constructor<?> constructor,
                                 @NotNull Class<?>[] constructorPrimaryTypes,
                                 @NotNull String[] namespaces,
                                 @NotNull Field[] fields) {
        this.clazz = clazz;
        this.constructor = constructor;
        this.constructorPrimaryTypes = constructorPrimaryTypes;
        this.namespaces = namespaces;
        this.fields = fields;
    }
}

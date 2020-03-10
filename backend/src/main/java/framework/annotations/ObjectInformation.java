package framework.annotations;

import java.util.List;

/**
 * Allows passing usefull information from {@link AnnotationsProcessor} to other classes.
 * @param <T> {@link T}
 */
public final class ObjectInformation<T> {
    public final Class<?>[] primaryKeyTypes;
    public final Class<?> clazz;
    public final List<T> data;

    ObjectInformation(Class<?>[] primaryKeyTypes, Class<?> clazz, List<T> data) {
        this.primaryKeyTypes = primaryKeyTypes;
        this.clazz = clazz;
        this.data = data;
    }
}

package DOIFramework.core.annotations;

import java.util.List;

/**
 * Allows passing usefull external information from {@link AnnotationsProcessor}
 * to other classes.
 */
public final class ObjectInformation {
    public final Class<?>[] primaryKeyTypes;
    public final Class<?> clazz;
    public final List<Object> data;

    ObjectInformation(Class<?>[] primaryKeyTypes, Class<?> clazz, List<Object> data) {
        this.primaryKeyTypes = primaryKeyTypes;
        this.clazz = clazz;
        this.data = data;
    }
}

package framework.annotations;

import framework.utilities.data.handle.IHandle;
import java.util.Map;

public class AnnotationInformation {
    public final Class<?> clazz;
    public final Map<Class<? extends IHandle>, String[]> mappedResources;

    public AnnotationInformation(Class<?> clazz, Map<Class<? extends IHandle>, String[]> mappedResources) {
        this.clazz = clazz;
        this.mappedResources = mappedResources;
    }
}

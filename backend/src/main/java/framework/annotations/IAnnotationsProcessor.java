package framework.annotations;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IAnnotationsProcessor {
    DataObject getDataObjectForFilename(String fileName);
    List<Object> initializeDataObjectsFromFileName(@NotNull List<Object[]> listWithInitArgs, @NotNull String file) throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}

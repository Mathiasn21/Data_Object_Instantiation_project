package framework.annotations;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IAnnotationsProcessor {
    DataObject getDataObjectForFilename(String fileName);
    Object initializeDataObject(@NotNull Object ...initArgs) throws InstantiationException;
    List<?> initializeDataObjects(@NotNull List<List<Object>> listWithInitArgs, @NotNull String file) throws InstantiationException;
}

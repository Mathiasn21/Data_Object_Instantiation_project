package framework.annotations;

import org.jetbrains.annotations.NotNull;

public interface IAnnotationsProcessor {
    Object initializeDataObject(@NotNull Object ...initArgs) throws InstantiationException;
}

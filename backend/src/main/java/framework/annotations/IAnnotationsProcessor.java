package framework.annotations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/** Interface describing a contract for processing annotations within the framework.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public interface IAnnotationsProcessor {
    <T> ObjectInformation<T> initializeDataObjects(@NotNull List<Object[]> listWithInitArgs, @NotNull String file)
            throws ReflectiveOperationException;

    @Nullable Class<?> getClassFromObjectSample(Object... objects);
    @Nullable Class<?> getClassFromObjectSample(String name);
}

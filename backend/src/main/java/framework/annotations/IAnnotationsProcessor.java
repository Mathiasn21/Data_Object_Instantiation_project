package framework.annotations;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/** Interface describing a contract for processing annotations within the framework.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public interface IAnnotationsProcessor {
    <T>List<T> initializeDataObjectsFromFileName(@NotNull List<Object[]> listWithInitArgs, @NotNull String file)
            throws ReflectiveOperationException;
}

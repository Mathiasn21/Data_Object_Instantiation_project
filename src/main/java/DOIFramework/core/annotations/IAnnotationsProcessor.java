package DOIFramework.core.annotations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/** Interface describing a contract for processing annotations within the framework.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public interface IAnnotationsProcessor {
    /**
     * @param listWithInitArgs {@link List}&lt;{@link Object}[]&gt;
     * @param file {@link String}
     * @return {@link ObjectInformation}
     * @throws ReflectiveOperationException ReflectiveOperationException
     */
    @NotNull ObjectInformation initializeDataObjects(@NotNull List<Object[]> listWithInitArgs, @NotNull String file)
            throws ReflectiveOperationException;

    /**
     * @param objects {@link Object}
     * @return {@link Class}&lt;?&gt;
     */
    @Nullable Class<?> getClassFromObjectSample(Object... objects);

    /**
     * @param name {@link String}
     * @return {@link Class}&lt;?&gt;
     */
    @Nullable Class<?> getClassFromObjectSample(String name);
}

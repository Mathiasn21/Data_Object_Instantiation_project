package doiframework.core.collectors;

import java.io.IOException;
import java.util.List;

/** Interface describing contract for all collectors
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public interface IDataCollector {
    /**
     * @throws IOException IOException
     */
    void collectData() throws IOException;

    /** Toggles compression of objects in the resource collections on or off.
     * By using compression each result from the compare that yields 0, is
     * only incrementing the existing counter instead of actually storing that object.
     * @param b boolean
     */
    void setCompression(boolean b);

    /**
     * @return {@link Class}&lt;?&gt;
     */
    Class<?> getClazz();

    /**
     * @return {@link List}&lt;{@link Object}&gt;
     */
    List<Object> getAllColumns();
}

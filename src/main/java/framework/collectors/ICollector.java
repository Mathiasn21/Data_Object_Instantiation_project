package framework.collectors;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Interface describing contract for all collectors
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public interface ICollector {
    /**
     * @throws IOException IOException
     */
    void collectData() throws IOException;

    /**Toggles compression of objects in the resource collections on or off.
     * @param b boolean
     */
    void setCompression(boolean b);

    /**
     * @param mb int
     */
    void setMaxMemoryMB(int mb);

    /**
     * @return {@link Class}&lt;?&gt;
     */
    Class<?> getClazz();

    /**
     * @return {@link List}&lt;{@link Object}&gt;
     */
    List<Object> getAllColumns();
}

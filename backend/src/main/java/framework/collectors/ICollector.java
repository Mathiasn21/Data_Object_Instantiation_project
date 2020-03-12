package framework.collectors;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Interface describing contract for all collectors
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface ICollector {
    /**
     * @throws IOException IOException
     */
    void CollectData() throws IOException;

    /**
     * @param list {@link List}&lt;{@link String}&gt;
     */
    void setPrimaryKeys(List<String> list);

    /**
     * @param key {@link Setting}
     * @param value {@link String}
     */
    void setSetting(Setting key, String value);

    /**
     * @param settings {@link Map}&lt;{@link Setting}, {@link String}&gt;
     */
    void setAllSettings(Map<Setting, String> settings);

    /**
     * @param mb int
     */
    void setMaxMemoryMB(int mb);

    /**
     * @return {@link List}&lt;{@link String}&gt;
     */
    List<String> getPrimaryKeys();

    /**
     * @return {@link Class}&lt;?&gt;[]
     */
    Class<?>[] getPrimaryKeyTypes();

    /**
     * @return {@link Class}&lt;?&gt;
     */
    Class<?> getClazz();



    /**
     * @return {@link List}&lt;{@link Object}&gt;
     */
    List<Object> getAllColumns();
}

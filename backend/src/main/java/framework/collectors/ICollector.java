package framework.collectors;

import framework.annotations.DataObject;
import framework.utilities.data.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Interface describing contract for all collectors
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface ICollector {
    /**
     * @return {@link List}&lt;{@link DataObject}&gt;
     */
    List<DataObject> getAllPrimaryColumns();

    /**
     * @return {@link List}&lt;{@link DataObject}&gt;
     */
    List<DataObject> getAllColumns();

    /**
     * @throws IOException IOException
     */
    void loadData() throws IOException;

    /**
     * @param key {@link Setting}
     * @param value {@link String}
     */
    void setSetting(Setting key, String value);

    /**
     * @param columnName {@link DataObject}
     * @return {@link List}&lt;{@link DataObject}&gt;
     */
    List<DataObject> getColumnBy(DataObject columnName);

    /**
     * @param list {@link List}&lt;{@link DataObject}&gt;
     */
    void setPrimaryColumns(List<DataObject> list);

    /**
     * @param settings {@link Map}&lt;{@link Setting}, {@link String}&gt;
     */
    void setAllSettings(Map<Setting, String> settings);

    //TODO: might be better to remove this
    /**
     * Might be redundant
     * @param name {@link DataObject}
     * @return {@link List}&lt;{@link DataObject}&gt;
     */
    List<DataObject> getCategoryBy(DataObject name);

    /**
     * @param mb int
     */
    void setMaxMemoryMB(int mb);
}

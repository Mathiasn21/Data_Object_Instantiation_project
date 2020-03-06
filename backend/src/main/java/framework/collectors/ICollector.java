package framework.collectors;

import framework.annotations.DataObject;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Interface describing contract for all collectors
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface ICollector {
    /**
     * @return String[]
     */
    List<DataObject> getAllPrimaryColumns();

    /**
     * @return String[][]
     */
    List<DataObject> getAllColumns();

    /**
     * @throws IOException IOException
     */
    void loadAndReadFile() throws IOException;

    /**
     * @param key Setting
     * @param value String
     */
    void setSetting(Setting key, String value);

    /**
     * @param columnName String
     */
    List<DataObject> getColumnBy(DataObject columnName);

    /**
     * @param list List<String>
     */
    void setPrimaryColumns(List<DataObject> list);

    void setAllSettings(Map<Setting, String> settings);

    List<DataObject> getCategoryBy(DataObject name);

    /**
     * @param mb int
     */
    void setMaxMemoryMB(int mb);

}

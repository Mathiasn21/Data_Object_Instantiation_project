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
     * @return String[]
     */
    String[] getAllPrimaryColumns();

    /**
     * @return String[][]
     */
    String[][] getAllColumns();

    /**
     * @throws IOException IOException
     */
    void loadAndReadFile() throws IOException;


    /**
     * @param name String
     * @return Item[]
     */
    Item[] getCategoryBy(String name);

    /**
     * @param key Setting
     * @param value String
     */
    void setSetting(Setting key, String value);

    /**
     * @param columnName String
     */
    String[] getColumnBy(String columnName);

    /**
     * @param primaryColumns String[]
     */
    void setPrimaryColumns(String[] primaryColumns);

    /**
     * @param list List<String>
     */
    void setPrimaryColumns(List<String> list);

    void setAllSettings(Map<Setting, String> settings);

    /**
     * @param mb int
     */
    void setMaxMemoryMB(int mb);

}

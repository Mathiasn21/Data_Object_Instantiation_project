package framework.dataCollection;

import java.io.IOException;

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
}
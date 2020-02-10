package framework.dataCollection;

import java.io.IOException;
import java.util.List;

public interface ICollector {
    String[] getAllPrimaryColumns();

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
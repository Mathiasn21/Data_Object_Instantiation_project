package framework.dataCollection;

import java.io.IOException;

public interface ICollector {
    String[] getAllPrimaryColumns();

    String[][] getAllColumns();

    /**
     * @throws IOException IOException
     */
    void loadAndReadFile() throws IOException;

    /**
     * @param name String
     */
    void getCategoryBy(String name);

    /**
     * @param key Setting
     * @param value String
     */
    void setSetting(Setting key, String value);
}
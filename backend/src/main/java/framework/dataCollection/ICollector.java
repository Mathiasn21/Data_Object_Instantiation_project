package framework.dataCollection;

import java.io.IOException;

public interface ICollector {
    String[] getAllPrimaryColumns();

    String[][] getAllColumns();

    void loadAndReadFile() throws IOException;
    void getCategoryBy(String name);
    void setSetting(Setting key, String value);
}
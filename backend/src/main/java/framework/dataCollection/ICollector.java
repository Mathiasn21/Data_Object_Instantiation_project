package framework.dataCollection;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface ICollector {
    void getAllPrimaryColumns();

    void getAllFilledColumns();

    void loadAndReadFile(String fileName) throws IOException;

    void loadAndReadFile(File file);

    void getCategoryBy(String name);

    void setSetting(Setting setting);

    void setSettings(Collection<Setting> settingCollection);
}
package framework.dataCollection;

import java.io.File;
import java.util.Collection;

public interface ICollector {
    void getAllPrimaryColumns();

    void getAllFilledColumns();

    void loadAndReadFile(String fileName);

    void loadAndReadFile(File file);

    void getCategoryBy(String name);

    void setSetting(Setting setting);

    void setSettings(Collection<Setting> settingCollection);
}
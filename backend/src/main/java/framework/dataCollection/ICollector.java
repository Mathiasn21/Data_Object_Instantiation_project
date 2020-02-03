package framework.dataCollection;

import java.util.Collection;

public interface ICollector {
    void getAllPrimaryColumns();
    void getAllFilledColumns();
    void loadAndReadFile();
    void getCategoryBy(String name);
    void setSetting(Setting setting);
    void setSettings(Collection<Setting> settingCollection);
}
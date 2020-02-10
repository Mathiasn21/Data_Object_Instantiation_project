package framework.dataCollection;

import java.util.HashMap;
import java.util.Map;

public abstract class Collector implements ICollector{
    private String[] primaryColumns;
    private final Map<Setting, String> settings = new HashMap<>();

    /**
     * @param primaryColumns String[]
     */
    protected void setPrimaryColumns(String[] primaryColumns){ this.primaryColumns = primaryColumns; }

    /**
     * @return String[]
     */
    @Override
    public String[] getAllPrimaryColumns() {
        return primaryColumns;
    }

    /**
     * @param name String
     */
    @Override
    public void getCategoryBy(String name) {
    }

    /**
     * @param key   Setting Setting
     * @param value String String
     */
    @Override
    public void setSetting(Setting key, String value) {
        settings.put(key, value);
    }


    /**
     * @param settings Map String String
     */
    protected final void setAllSettings(Map<Setting, String> settings){
        this.settings.putAll(settings);
    }

    /**
     * @param setting Setting
     * @return String
     */
    protected final String getSetting(Setting setting){
        return settings.get(setting);
    }
}

package framework.dataCollection;

import java.util.HashMap;
import java.util.Map;

public abstract class Collector implements ICollector{
    private String[] primaryColumns;
    private final Map<Setting, String> settings = new HashMap<>();

    //initialize properties
    protected void setPrimaryColumns(String[] primaryColumns){ this.primaryColumns = primaryColumns; }

    @Override
    public String[] getAllPrimaryColumns() {
        return primaryColumns;
    }

    @Override
    public void getCategoryBy(String name) {

    }

    @Override
    public void setSetting(Setting key, String value) {
        settings.put(key, value);
    }


    protected final void setAllSettings(Map<Setting, String> settings){
        this.settings.putAll(settings);
    }

    protected final String getSetting(Setting setting){
        return settings.get(setting);
    }
}

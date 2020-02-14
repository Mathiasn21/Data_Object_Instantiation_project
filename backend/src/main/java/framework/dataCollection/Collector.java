package framework.dataCollection;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Collector implements ICollector{
    private String[] primaryColumns;
    private final Map<Setting, String> settings = new HashMap<>();


    protected void setPrimaryColumns(@NotNull List<String> list) {
        setPrimaryColumns(list.toArray(new String[0]));
    }

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

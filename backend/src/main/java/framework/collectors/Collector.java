package framework.collectors;

import framework.collectors.factory.CollectorFactory;
import framework.collectors.factory.ICollectorFactory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Class for collecting data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public abstract class Collector implements ICollector{
    private String[] primaryColumns;
    private final Map<Setting, String> settings = new HashMap<>();

    /**
     * @param list {@link List}
     */
    @Override
    public final void setPrimaryColumns(@NotNull List<String> list) {
        setPrimaryColumns(list.toArray(new String[0]));
    }

    /**
     * @param primaryColumns String[]
     */
    @Override
    public final void setPrimaryColumns(String[] primaryColumns){ this.primaryColumns = primaryColumns; }

    /**
     * @return String[]
     */
    @Override
    public final String[] getAllPrimaryColumns() {
        return primaryColumns;
    }

    /**
     * @param key   Setting Setting
     * @param value String String
     */
    @Override
    public final void setSetting(Setting key, String value) {
        settings.put(key, value);
    }

    /**
     * @param settings Map String String
     */
    @Override
    public final void setAllSettings(Map<Setting, String> settings){
        this.settings.putAll(settings);
    }

    /**
     * @param setting Setting
     * @return String
     */
    protected final String getSetting(@NotNull Setting setting){
        return settings.get(setting);
    }

    /**
     * Returns an unmodifiable map see {@link Collections}
     * for more information
     * @return Map {@link Setting}, String.
     */
    @NotNull
    @Contract(pure = true)
    public final Map<Setting, String> getSettings(){ return Collections.unmodifiableMap(settings); }

    /**
     * @param extension String
     * @return ICollector {@link ICollector}
     */
    @NotNull
    public static ICollector fromFileExtension(String extension){
        ICollectorFactory<ICollector> factory = new CollectorFactory();
        return factory.createCollectorFrom(extension);
    }


    /**
     * @param mb int
     */
    @Contract(pure = true)
    @Override
    public final void setMaxMemoryMB(int mb){
        //TODO: implement setMaxMemoryMB()
        //TODO: Describe set max memory of what????
    }

}

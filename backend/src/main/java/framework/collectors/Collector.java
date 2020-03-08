package framework.collectors;

import framework.annotations.AnnotationsProcessor;
import framework.annotations.DataObject;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

/** Class responsible for collecting data from a resource {@link Resource} using a handler {@link IHandle}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public final class Collector implements ICollector{
    private static final AnnotationsProcessor annotationProcessor = new AnnotationsProcessor();

    private List<DataObject> primaryColumns;
    private final Map<Setting, String> settings = new HashMap<>();
    private TreeMap<String, DataObject> rbTree = new TreeMap<>();
    private List<String[]> informationalRows = new ArrayList<>();

    private IHandle dataHandler;
    private Resource resource;

    /**
     * @param resource {@link Resource}
     * @param dataHandler {@link IHandle}
     */
    Collector(Resource resource, IHandle dataHandler) {
        this.resource = resource;
        this.dataHandler = dataHandler;
    }

    /**
     * @param resource {@link Resource}
     * @param dataHandler {@link IHandle}
     * @return
     */
    @NotNull
    @Contract("_, _ -> new")
    public static CollectorBuilder getBuilder(Resource resource, IHandle dataHandler) {
        return new CollectorBuilder(resource, dataHandler);
    }

    /**
     * Sets the primary columns to match and find
     * @param primaryColumns {@link List}&lt;{@link DataObject}&gt;
     */
    @Override
    public final void setPrimaryColumns(List<DataObject> primaryColumns){ this.primaryColumns = primaryColumns; }

    /**
     * Columns that describe the values inherent in a dataset.
     * @return {@link List}&lt;{@link DataObject}&gt;
     */
    @Override
    public final List<DataObject> getAllPrimaryColumns() {
        return primaryColumns;
    }

    @Override
    public List<DataObject> getAllColumns() {
        return new ArrayList<>(rbTree.values());
    }

    @Override
    public void CollectData() throws IOException {

    }

    /**
     * @param name {@link DataObject}
     * @return {@link List}&lt;{@link DataObject}&gt;
     */
    @Override
    public List<DataObject> getCategoryBy(DataObject name) {
        List<DataObject> list = new ArrayList<>();
        if(rbTree.containsValue(name)){
            list.add(name);
        }
        return Collections.unmodifiableList(list) ;
    }


    /**
     * will be moved to extractor.
     * @param columnName String
     * @return {@link List}&lt;{@link DataObject}&gt;
     */
    @Deprecated
    public List<DataObject> getColumnBy(DataObject columnName) {
        //TODO: implement method
        return new ArrayList<>();
    }

    /**
     *
     * @param key {@link Setting}
     * @param value String
     */
    @Override
    public final void setSetting(@NotNull Setting key, @NotNull String value) {
        settings.put(key, value);
    }


    /**
     * Sets all settings from a given map.
     * Will overwrite all existing settings with the new value.
     * @param settings {@link Map}&lt;String, String&gt;
     */
    @Override
    public final void setAllSettings(Map<@NotNull Setting, @NotNull String> settings){
        this.settings.putAll(settings);
    }

    /**
     * Returns an unmodifiable map see {@link Collections}
     * for more information
     * @return Map {@link Setting}, String.
     */
    @Contract(pure = true)
    public final @NotNull Map<@NotNull Setting, @NotNull String> getSettings(){ return Collections.unmodifiableMap(settings); }

    /**
     * Sets max memory that this collector is allowed to utilize.
     * Keeps the internal data structure from filling up.
     * @param mb int
     */
    @Contract(pure = true)
    @Override
    public final void setMaxMemoryMB(int mb){
        //TODO: implement setMaxMemoryMB()
        //TODO: Describe set max memory of what????
    }

}

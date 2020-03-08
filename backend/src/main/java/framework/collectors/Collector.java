package framework.collectors;

import framework.annotations.AnnotationsProcessor;
import framework.annotations.DataObject;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

/** Class for collecting data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class Collector implements ICollector{
    private static final AnnotationsProcessor annotationProcessor = new AnnotationsProcessor();

    private List<DataObject> primaryColumns;
    private final Map<Setting, String> settings = new HashMap<>();
    private TreeMap<String, DataObject> rbTree = new TreeMap<>();
    private List<String[]> informationalRows = new ArrayList<>();

    private IHandle handler;
    private Resource resource;
    Collector() {

    }

    @NotNull
    @Contract(" -> new")
    public static CollectorBuilder getBuilder() {
        return new CollectorBuilder();
    }

    /**
     * @param primaryColumns List<DataObject>
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
    public void loadData() throws IOException {

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

    public List<DataObject> getColumnBy(DataObject columnName) {
        //TODO: implement method
        return new ArrayList<>();
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
     * @return List String[]
     */
    public List<String[]> getInformationalRows() {
        //TODO: add informational rows
        return informationalRows;
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

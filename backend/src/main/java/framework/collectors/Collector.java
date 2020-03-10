package framework.collectors;

import framework.annotations.AnnotationsProcessor;
import framework.annotations.DataObject;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/** Class responsible for collecting data from a resource {@link Resource} using a handler {@link IHandle}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0.0
 */
public final class Collector implements ICollector{
    private static final AnnotationsProcessor annotationProcessor = new AnnotationsProcessor();

    private final Map<Setting, String> settings = new HashMap<>();
    private TreeMap<String, Object> rbTreeSet = new TreeMap<>();
    private List<String> primaryKeys;
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
     * Collects all data from a resource utilizing the stored handler.
     * @throws IOException IOException
     */
    @Override
    public void CollectData() throws IOException {
        long start = System.currentTimeMillis();
        List<Object[]> initArgs = dataHandler.handle(resource.getData());
        try {
            List<Object> objectList = annotationProcessor.initializeDataObjectsFromFileName(initArgs, resource.getName());
            System.out.println("Size is: " + objectList.size());
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * Sets the primary columns to match and find
     * @param primaryKeys {@link List}&lt;{@link String}&gt;
     */
    @Override
    public final void setPrimaryKeys(List<String> primaryKeys){ this.primaryKeys = primaryKeys; }


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

    /**
     * Columns that describe the values inherent in a dataset.
     * @return {@link List}&lt;{@link String}&gt;
     */
    @Override
    public final List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    /**
     * Returns all column data excluding primary keys
     * @return {@link List}&lt;{@link Object}&gt;
     */
    @NotNull
    @Override
    public List<Object> getAllColumns() {
        return Collections.unmodifiableList(List.of(rbTreeSet.values().toArray()));
    }


    /**
     * Returns an unmodifiable map see {@link Collections}
     * for more information
     * @return Map {@link Setting}, String.
     */
    @Contract(pure = true)
    public final @NotNull Map<@NotNull Setting, @NotNull String> getSettings(){ return Collections.unmodifiableMap(settings); }


    /**
     * @param resource {@link Resource}
     * @param dataHandler {@link IHandle}
     * @return {@link CollectorBuilder}
     */
    @NotNull
    @Contract("_, _ -> new")
    public static CollectorBuilder getBuilder(Resource resource, IHandle dataHandler) {
        return new CollectorBuilder(resource, dataHandler);
    }
}

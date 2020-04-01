package framework.collectors;

import framework.annotations.AnnotationsProcessor;
import framework.annotations.ObjectInformation;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import framework.utilities.data.structure.ITree;
import framework.utilities.data.structure.Node;
import framework.utilities.data.structure.RBTree;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

/** Class responsible for collecting data from a resource {@link Resource} using a handler {@link IHandle}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0.0
 */
public final class Collector implements ICollector{
    private static final AnnotationsProcessor annotationProcessor = new AnnotationsProcessor();

    private final IHandle dataHandler;
    private final Resource resource;
    private List<String> primaryKeys;
    private Class<?>[] primaryTypes;
    private Class<?> clazz;
    private ITree<Object> rbTree;
    private Comparator<Object> comparator = null;
    private boolean compression = false;

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
    public void collectData() throws IOException {
        List<Object[]> initArgs = dataHandler.handle(resource.getData());
        rbTree = new RBTree<>(comparator, compression);

        try {
            ObjectInformation objectObjectInformation = annotationProcessor.initializeDataObjects(initArgs, resource.getNameSpace()[0]);
            for (Object o : objectObjectInformation.data) { rbTree.insert(o); }

            primaryTypes = objectObjectInformation.primaryKeyTypes;
            clazz = objectObjectInformation.clazz;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the primary columns to match and find
     * @param primaryKeys {@link List}&lt;{@link String}&gt;
     */
    @Override
    public final void setPrimaryKeys(List<String> primaryKeys){ this.primaryKeys = primaryKeys; }

    @Override
    public void setCompressionOn(boolean b) {
        this.compression = b;
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
     * @return Class&lt;?&gt;&gt;[]
     */
    @NotNull
    @Contract(pure = true)
    @Override
    public Class<?>[] getPrimaryKeyTypes() { return primaryTypes; }

    /**
     * @return Class&lt;?&gt;&gt;
     */
    @NotNull
    @Contract(pure = true)
    @Override
    public Class<?> getClazz() { return clazz; }

    /**
     * Returns all column data excluding primary keys
     * @return {@link List}&lt;{@link Object}&gt;
     */
    @NotNull
    @Override
    public List<Object> getAllColumns() {
        Iterator<Node<Object>> iterator = rbTree.inorderTraversal();
        List<Object> res = new ArrayList<>();
        while(iterator.hasNext()){ res.add(iterator.next().getT()); }
        return res;
    }

    /**
     * @param resource {@link Resource}
     * @param dataHandler {@link IHandle}
     * @return {@link CollectorBuilder}
     */
    @NotNull
    @Contract("_, _ -> new")
    public static CollectorBuilder newCollector(Resource resource, IHandle dataHandler) {
        return new CollectorBuilder(resource, dataHandler);
    }
}

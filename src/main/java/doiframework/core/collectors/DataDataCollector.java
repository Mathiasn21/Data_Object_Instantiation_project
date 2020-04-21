package doiframework.core.collectors;

import doiframework.core.annotations.AnnotationsProcessor;
import doiframework.core.annotations.ObjectInformation;
import doiframework.core.observer.EventObserver;
import doiframework.core.observer.events.DataCollectorFinishedEvent;
import doiframework.core.observer.events.ExceptionEvent;
import doiframework.core.observer.events.IEvent;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.IHandle;
import doiframework.utilities.collections.ITree;
import doiframework.utilities.collections.Node;
import doiframework.utilities.collections.RBTree;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

/** Class responsible for collecting dataSource from a dataSource {@link DataSource} using a handler {@link IHandle}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.9.4
 */
public final class DataDataCollector implements IDataCollector {
    private static final AnnotationsProcessor annotationProcessor = new AnnotationsProcessor();

    private final IHandle dataHandler;
    private final DataSource dataSource;
    private Class<?> clazz;
    private ITree<Object> rbTree;
    private Comparator<Object> comparator = null;
    private boolean compression = false;

    /**
     * @param dataSource {@link DataSource}
     * @param dataHandler {@link IHandle}
     */
    DataDataCollector(DataSource dataSource, IHandle dataHandler) {
        this.dataSource = dataSource;
        this.dataHandler = dataHandler;
    }

    /**
     * Collects all dataSource from a dataSource utilizing the stored handler.
     * @throws IOException IOException
     */
    @Override
    public void collectData() throws IOException {
        List<Object[]> initArgs = dataHandler.handle(dataSource.getData());
        rbTree = new RBTree<>(comparator, compression);
        try {
            ObjectInformation objectObjectInformation = annotationProcessor.initializeDataObjects(initArgs, dataSource.getNameSpaces()[0]);
            for (Object o : objectObjectInformation.data) { rbTree.insert(o); }

            clazz = objectObjectInformation.clazz;
            raise(new DataCollectorFinishedEvent(this));
        } catch (ReflectiveOperationException e) { raise(new ExceptionEvent(this, e)); }
    }

    /** Toggles compression of objects in the dataSource collections on or off.
     * By using compression each result from the compare that yields 0, is
     * only incrementing the existing counter instead of actually storing that object.
     * @param b boolean
     */
    @Override
    public void setCompression(boolean b) {
        this.compression = b;
    }

    /**
     * @return Class&lt;?&gt;&gt;
     */
    @NotNull
    @Contract(pure = true)
    @Override
    public Class<?> getClazz() { return clazz; }

    /**
     * Returns all collected dataSource traversed in order
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

    private void raise(@NotNull IEvent event) { EventObserver.registerEventFrom(event); }


    /**
     * @param dataSource {@link DataSource}
     * @param dataHandler {@link IHandle}
     * @return {@link CollectorBuilder}
     */
    @NotNull
    @Contract("_, _ -> new")
    public static CollectorBuilder newCollector(DataSource dataSource, IHandle dataHandler) {
        return new CollectorBuilder(dataSource, dataHandler);
    }

    @NotNull
    @Override
    public String toString() {
        var iterator = rbTree.inorderTraversal();
        StringBuilder builder = new StringBuilder();
        while(iterator.hasNext()){
            builder.append(iterator.next().getT()).append("\n");
        }
        return builder.toString();
    }
}

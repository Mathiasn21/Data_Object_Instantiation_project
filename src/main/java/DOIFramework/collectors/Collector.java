package DOIFramework.collectors;

import DOIFramework.annotations.AnnotationsProcessor;
import DOIFramework.annotations.ObjectInformation;
import DOIFramework.observer.EventObserver;
import DOIFramework.observer.events.CollectorFinishedEvent;
import DOIFramework.observer.events.ExceptionEvent;
import DOIFramework.observer.events.IEvent;
import DOIFramework.resource.Resource;
import DOIFramework.utilities.handlers.IHandle;
import DOIFramework.utilities.collections.ITree;
import DOIFramework.utilities.collections.Node;
import DOIFramework.utilities.collections.RBTree;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

/** Class responsible for collecting resource from a resource {@link Resource} using a handler {@link IHandle}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.9.4
 */
public final class Collector implements ICollector {
    private static final AnnotationsProcessor annotationProcessor = new AnnotationsProcessor();

    private final IHandle dataHandler;
    private final Resource resource;
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
     * Collects all resource from a resource utilizing the stored handler.
     * @throws IOException IOException
     */
    @Override
    public void collectData() throws IOException {
        List<Object[]> initArgs = dataHandler.handle(resource.getData());
        rbTree = new RBTree<>(comparator, compression);

        try {
            ObjectInformation objectObjectInformation = annotationProcessor.initializeDataObjects(initArgs, resource.getNameSpaces()[0]);
            for (Object o : objectObjectInformation.data) { rbTree.insert(o); }

            clazz = objectObjectInformation.clazz;
            raise(new CollectorFinishedEvent(this));
        } catch (ReflectiveOperationException e) { raise(new ExceptionEvent(this, e)); }
    }

    @Override
    public void setCompression(boolean b) {
        this.compression = b;
    }

    /**
     * Sets max memory that this collector is allowed to utilize.
     * Keeps the internal resource collections from filling up.
     * @param mb int
     */
    @Contract(pure = true)
    @Override
    public final void setMaxMemoryMB(int mb){
        //TODO: implement setMaxMemoryMB()
        //TODO: Describe set max memory of what????
    }

    /**
     * @return Class&lt;?&gt;&gt;
     */
    @NotNull
    @Contract(pure = true)
    @Override
    public Class<?> getClazz() { return clazz; }

    /**
     * Returns all collected resource traversed in order
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

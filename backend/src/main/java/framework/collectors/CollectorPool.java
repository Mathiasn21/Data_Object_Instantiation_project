package framework.collectors;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class CollectorPool implements ICollectorPool{
    private final List<ICollector> collectors;

    CollectorPool(List<ICollector> collectors) { this.collectors = collectors; }

    @Override
    public void collectAllData() throws IOException {
        for (ICollector collector : collectors) {
            collector.collectData();
        }
    }

    /**
     * @param resources {@link List}&lt;{@link Resource}&gt;
     * @param dataHandler {@link IHandle}
     * @return {@link CollectorBuilder}
     */
    @NotNull
    @Contract("_, _ -> new")
    public static CollectorPoolBuilder newCollectors(List<Resource> resources, IHandle dataHandler) {
        return new CollectorPoolBuilder(resources, dataHandler);
    }
}
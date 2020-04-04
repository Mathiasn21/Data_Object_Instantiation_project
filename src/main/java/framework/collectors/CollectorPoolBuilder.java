package framework.collectors;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class CollectorPoolBuilder {
    private final List<CollectorBuilder> collectorBuilders;
    CollectorPoolBuilder(@NotNull List<Resource> resources, @NotNull IHandle handler) {
        List<CollectorBuilder> collectorBuilders = new ArrayList<>(resources.size());
        for (Resource resource : resources) {
            collectorBuilders.add(Collector.newCollector(resource, handler));
        }
        this.collectorBuilders = collectorBuilders;
    }

    @Contract("_ -> this")
    @NotNull
    public final CollectorPoolBuilder setGloballyMaxMemoryMB(int mb){
        for (CollectorBuilder builder : collectorBuilders) {
            builder.setMaxMemoryMB(mb);
        }
        return this;
    }

    @Contract(pure = true)
    @NotNull
    public final ICollectorPool buildAll(){
        List<ICollector> collectors = new ArrayList<>(collectorBuilders.size());
        for (CollectorBuilder builder : collectorBuilders) {
            collectors.add(builder.build());
        }
        return new CollectorPool(collectors);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public final String toString() { return "Collector builder " + collectorBuilders.toString(); }
}

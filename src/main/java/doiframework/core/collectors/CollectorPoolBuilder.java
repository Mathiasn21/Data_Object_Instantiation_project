package doiframework.core.collectors;

import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class CollectorPoolBuilder {
    private final List<CollectorBuilder> collectorBuilders;
    CollectorPoolBuilder(@NotNull List<DataSource> dataSources, @NotNull IHandle handler) {
        List<CollectorBuilder> collectorBuilders = new ArrayList<>(dataSources.size());
        for (DataSource dataSource : dataSources) {
            collectorBuilders.add(DataDataCollector.newCollector(dataSource, handler));
        }
        this.collectorBuilders = collectorBuilders;
    }

    @Contract(pure = true)
    @NotNull
    public final IDataCollectorPool buildAll(){
        List<IDataCollector> collectors = new ArrayList<>(collectorBuilders.size());
        for (CollectorBuilder builder : collectorBuilders) {
            collectors.add(builder.build());
        }
        return new DataDataCollectorPool(collectors);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public final String toString() { return "DataDataCollector builder " + collectorBuilders.toString(); }
}

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
public final class DataCollectorPoolBuilder {
    private final List<DataCollectorBuilder> dataCollectorBuilders;
    DataCollectorPoolBuilder(@NotNull List<DataSource> dataSources, @NotNull IHandle handler) {
        List<DataCollectorBuilder> dataCollectorBuilders = new ArrayList<>(dataSources.size());
        for (DataSource dataSource : dataSources) {
            dataCollectorBuilders.add(DataCollector.newCollector(dataSource, handler));
        }
        this.dataCollectorBuilders = dataCollectorBuilders;
    }

    @Contract(pure = true)
    @NotNull
    public final IDataCollectorPool buildAll(){
        List<IDataCollector> collectors = new ArrayList<>(dataCollectorBuilders.size());
        for (DataCollectorBuilder builder : dataCollectorBuilders) {
            collectors.add(builder.build());
        }
        return new DataCollectorPool(collectors);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public final String toString() { return "DataCollector builder " + dataCollectorBuilders.toString(); }
}

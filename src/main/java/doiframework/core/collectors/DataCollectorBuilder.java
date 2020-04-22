package doiframework.core.collectors;

import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.IDataHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class for creating a builder pattern for DataCollector class
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.0.0
 */
public final class DataCollectorBuilder {
    private final IDataCollector collector;

    DataCollectorBuilder(DataSource dataSource, IDataHandler dataHandler){ this.collector = new DataCollector(dataSource, dataHandler); }

    @Contract(pure = true)
    @NotNull
    public final IDataCollector build(){
        return collector;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public final String toString() { return this.collector.toString(); }
}

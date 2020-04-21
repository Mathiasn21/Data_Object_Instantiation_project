package doiframework.core.collectors;

import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class for creating a builder pattern for DataDataCollector class
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.0.0
 */
public final class CollectorBuilder {
    private final IDataCollector collector;

    CollectorBuilder(DataSource dataSource, IHandle dataHandler){ this.collector = new DataDataCollector(dataSource, dataHandler); }

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

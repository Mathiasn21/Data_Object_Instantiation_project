package doiframework.core.collectors;

import doiframework.core.resource.Resource;
import doiframework.utilities.handlers.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class for creating a builder pattern for Collector class
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.0.0
 */
public final class CollectorBuilder {
    private final ICollector collector;

    CollectorBuilder(Resource resource, IHandle dataHandler){ this.collector = new Collector(resource, dataHandler); }

    @Contract(pure = true)
    @NotNull
    public final ICollector build(){
        return collector;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public final String toString() { return this.collector.toString(); }
}

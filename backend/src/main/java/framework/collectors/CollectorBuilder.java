package framework.collectors;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Class for creating a builder pattern for Collector class
 * @author Maria Pedersen Github: https://github.com/marped
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.0.0
 */
public final class CollectorBuilder {
    private final ICollector collector;

    CollectorBuilder(Resource resource, IHandle dataHandler){
        this.collector = new Collector(resource, dataHandler);
    }

    @Contract("_ -> this")
    @NotNull
    public final CollectorBuilder setPrimaryColumns(List<String> primaryColumns){
        collector.setPrimaryKeys(primaryColumns);
        return this;
    }

    @Contract("_, _ -> this")
    @NotNull
    public final CollectorBuilder setSetting(Setting key, String value) {
        collector.setSetting(key, value);
        return this;
    }


    @Contract("_ -> this")
    @NotNull
    public final CollectorBuilder setAllSettings(Map<Setting, String> settings){
        collector.setAllSettings(settings);
        return this;
    }

    @Contract("_ -> this")
    @NotNull
    public final CollectorBuilder setMaxMemoryMB(int mb){
        collector.setMaxMemoryMB(mb);
        return this;
    }

    @Contract(pure = true)
    @NotNull
    public final ICollector build(){
        return collector;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public final String toString() {
        return "Collector builder";
    }

    /*TODO: Allow this class to be extended with its own implementations
     *  This could be done either through interfaces or a better way, through inheritance.
     * */
}

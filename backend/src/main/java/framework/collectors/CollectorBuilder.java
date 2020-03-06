package framework.collectors;

import java.util.List;
import java.util.Map;

/**
 * Class for creating a builder pattern for Collector class
 * @author Maria Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class CollectorBuilder {

    private final ICollector collector;

    public CollectorBuilder(String extension) {
        this.collector = Collector.fromFileExtension(extension);
    }

    public final CollectorBuilder setPrimaryColumns(String[] primaryColumns){
        collector.setPrimaryColumns(primaryColumns);
        return this;
    }

    public final CollectorBuilder setPrimaryColumns(List<String> list){
        collector.setPrimaryColumns(list);
        return this;
    }

    public final CollectorBuilder setSetting(Setting key, String value) {
        collector.setSetting(key, value);
        return this;
    }

    public final CollectorBuilder setAllSettings(Map<Setting, String> settings){
        collector.setAllSettings(settings);
        return this;
    }

    public final CollectorBuilder setMaxMemoryMB(int mb){
        collector.setMaxMemoryMB(mb);
        return this;
    }

    public ICollector build(){
        return collector;
    }

    @Override
    public String toString() {
        return "Collector builder";
    }
}

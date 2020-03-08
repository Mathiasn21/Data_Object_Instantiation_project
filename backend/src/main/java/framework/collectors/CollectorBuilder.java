package framework.collectors;

import framework.annotations.DataObject;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;

import java.util.List;
import java.util.Map;

/**
 * Class for creating a builder pattern for Collector class
 * @author Maria Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class CollectorBuilder {
    private final ICollector collector;

    public CollectorBuilder(){
        this.collector = new Collector();
    }

    public final CollectorBuilder setPrimaryColumns(List<DataObject> primaryColumns){
        collector.setPrimaryColumns(primaryColumns);
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

    public CollectorBuilder setResource(Resource resource) {
        return null;
    }

    public ICollector build(){
        return collector;
    }

    @Override
    public String toString() {
        return "Collector builder";
    }

    public CollectorBuilder setDataHandler(IHandle dataHandler) {
        return null;
    }
}

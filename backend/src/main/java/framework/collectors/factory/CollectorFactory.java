package framework.collectors.factory;

import framework.collectors.CSVCollector;
import framework.collectors.ICollector;
import framework.collectors.JSONCollector;

import java.util.HashMap;
import java.util.Map;

public class CollectorFactory implements ICollectorFactory<ICollector> {
    private static final Map<String, Class<? extends ICollector>> strMappedToCollectors = new HashMap<>();
    static{
        //TODO: extract mappings into its on config file
        strMappedToCollectors.put("csv", CSVCollector.class);
        strMappedToCollectors.put("json", JSONCollector.class);
    }


    @Override
    public ICollector createCollectorFrom(String fileExtension) {
        Class<? extends ICollector> collectorClazz = strMappedToCollectors.get(fileExtension);
        ICollector collector = null;
        if(collectorClazz == CSVCollector.class){
            collector = new CSVCollector();
        }else if(collectorClazz == JSONCollector.class) {
            collector = new JSONCollector();
        }

        return collector;
    }

    @Override
    public ICollector createCollectorFrom(Class<ICollector> clazz) {
        return null;
    }
}

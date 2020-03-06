package framework.collectors.factory;

import framework.collectors.ICSVCollector;
import framework.collectors.ICollector;
import framework.collectors.JSONCollector;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class CollectorFactory implements ICollectorFactory<ICollector> {
    private static final Map<String, Class<? extends ICollector>> strMappedToCollectors = new HashMap<>();
    static{
        //TODO: extract mappings into its on config file
        strMappedToCollectors.put("csv", ICSVCollector.class);
        strMappedToCollectors.put("json", JSONCollector.class);
    }


    @Override
    public ICollector createCollectorFrom(String fileExtension) {
        Class<? extends ICollector> collectorClazz = strMappedToCollectors.get(fileExtension);
        ICollector collector = null;
        if(collectorClazz == ICSVCollector.class){
            collector = new ICSVCollector();
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

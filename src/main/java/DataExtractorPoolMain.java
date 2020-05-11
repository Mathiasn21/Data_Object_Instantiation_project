import doiframework.core.collectors.DataCollectorPool;
import doiframework.core.collectors.IDataCollectorPool;
import doiframework.core.extractors.DataExtractorPool;
import doiframework.core.extractors.IDataExtractorPool;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.CSVHandler;
import doiframework.utilities.handlers.IDataHandler;
import doiframework.utilities.handlers.JSONHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataExtractorPoolMain {
    public static void main(String[] args) throws IOException {
        var dataCollectorPool = genCollectorPool();
        dataCollectorPool.collectAllData();
        dataCollectorPool.iterator().forEachRemaining((collector) -> System.out.println("Collector: " + collector.getAllObjects() + "\n"));

        IDataExtractorPool dataExtractorPool = new DataExtractorPool(dataCollectorPool);
        var res = dataExtractorPool.extractAllColumnsFromFields();

        res.forEach((k, v) -> {
            System.out.println("key: " + k  + "\n\t\tValues: ");
            v.forEach((k1, v1) -> System.out.println("\t\t\t\t" + v1));
        });
    }

    @NotNull
    private static IDataCollectorPool genCollectorPool() {
        String[] path = {System.getProperty("user.dir") + "/files/showcaseAPI.csv", System.getProperty("user.dir") + "/files/showcaseAPI.json"};
        List<DataSource> sourceList = DataSource.newResource().fromFiles(path).buildAll();
        Map<DataSource, IDataHandler> map = new HashMap<>();
        map.put(sourceList.get(0), new CSVHandler());
        map.put(sourceList.get(1), new JSONHandler());

        //Alternative way of declaring multiple DataSources
        //List<DataSource> sourceList2 = DataSource.newResource().fromFiles(path, path).buildAll();
        return DataCollectorPool.newCollectors(map).buildAll();
    }
}

import doiframework.core.collectors.DataCollectorPool;
import doiframework.core.collectors.IDataCollectorPool;
import doiframework.core.extractors.DataExtractorPool;
import doiframework.core.extractors.IDataExtractorPool;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.CSVHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class DataExtractorPoolMain {
    public static void main(String[] args) throws IOException {
        var dataCollectorPool = genCollectorPool();
        dataCollectorPool.collectAllData();

        IDataExtractorPool dataExtractorPool = new DataExtractorPool(dataCollectorPool);
        var res = dataExtractorPool.extractAllColumnsFromFields();
        res.forEach((k, v) -> {
            System.out.println("key: " + k  + "\n\t\tValues: ");
            v.forEach((k1, v1) -> System.out.println("\t\t\t\t" + v1));
        });
    }

    @NotNull
    private static IDataCollectorPool genCollectorPool() {
        String path = System.getProperty("user.dir") + "/files/showcaseAPI.csv";
        String path2 = System.getProperty("user.dir") + "/files/showcaseAPI2.csv";
        List<DataSource> sourceList = DataSource.newResource().fromFile(path).fromFile(path2).buildAll();

        //Alternative way of declaring multiple DataSources
        //List<DataSource> sourceList2 = DataSource.newResource().fromFiles(path, path).buildAll();
        return DataCollectorPool.newCollectors(sourceList, new CSVHandler()).buildAll();
    }
}

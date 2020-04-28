import doiframework.core.collectors.DataCollectorPool;
import doiframework.core.collectors.IDataCollectorPool;
import doiframework.core.extractors.DataExtractorPool;
import doiframework.core.extractors.IDataExtractor;
import doiframework.core.extractors.IDataExtractorPool;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.CSVHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class DataExtractorPoolMain {
    public static void main(String[] args) {
        var dataCollectorPool = genCollectorPool();

        IDataExtractorPool dataExtractorPool = new DataExtractorPool(dataCollectorPool);
        dataExtractorPool.extractAllColumnsFromFields();
    }

    @NotNull
    private static IDataCollectorPool genCollectorPool() {
        String path = System.getProperty("user.dir") + "/files/showcaseAPI.csv" ;
        List<DataSource> sourceList = DataSource.newResource().fromFile(path).fromFile(path).buildAll();

        //Alternative way of declaring multiple DataSources
        //List<DataSource> sourceList2 = DataSource.newResource().fromFiles(path, path).buildAll();
        return DataCollectorPool.newCollectors(sourceList, new CSVHandler()).buildAll();
    }
}

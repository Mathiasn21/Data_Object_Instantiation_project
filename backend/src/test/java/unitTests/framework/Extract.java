package unitTests.framework;

import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.extractors.Extractor;
import framework.extractors.IExtractor;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Extract {
    @Test
    void single_column() throws IOException {
        ICollector collector = genCollector();
        IExtractor extractor = new Extractor<>(collector);
        Class<?>[] types = collector.getPrimaryKeyTypes();

        extractor.extractAllColumns();
    }

    @Test
    void several_columns(){

    }




    @NotNull
    private ICollector genCollector() throws IOException {
        String path = System.getProperty("user.dir") + "/files/simpleCSV.csv" ;
        Resource resource = Resource.getBuilder().fromFile(path).build();
        IHandle handler = new CSVHandler();
        handler.setPrimaryKeyTypes(String.class, double.class, int.class);
        ICollector collector = Collector.getBuilder(resource, handler).build();
        collector.CollectData();
        return collector;
    }
}

package framework.collectData;

import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Collect {
    @Test
    void data_from_file() throws IOException {
        Resource resource = Resource.getBuilder().fromFile("").build();
        ICollector collector = Collector.getBuilder(resource, new CSVHandler()).build();
        collector.CollectData();

        //IWriteData writeData = new WriteData();
        //writeData.toFile().given("","");
    }
}

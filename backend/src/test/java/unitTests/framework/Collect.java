package unitTests.framework;

import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Collect {
    @Test
    void data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;
        Resource resource = Resource.getBuilder().fromFile(path).build();
        ICollector collector = Collector.getBuilder(resource, new JSONHandler()).build();
        collector.CollectData();
    }
}

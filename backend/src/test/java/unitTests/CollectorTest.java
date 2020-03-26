package unitTests;

import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class CollectorTest {
    @Test
    void data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.CollectData();
        System.out.println(collector.getAllColumns());
    }

    @Test
    void data_from_file2() throws IOException {
        String path = System.getProperty("user.dir") + "/files/testingJSONFile.json" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.CollectData();
        System.out.println(collector.getAllColumns());
    }

    @Test
    void data_from_file3() throws IOException {
        String path = System.getProperty("user.dir") + "/files/primitiveJSONtypes.json" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.CollectData();
        System.out.println(collector.getAllColumns());
    }

    @Test
    void data_from_URL() {
        //TODO: implement correct url for an json api

        Assertions.assertDoesNotThrow(() -> {
            URL url = new URL("http://example.com");
            Resource resource = Resource.newResource().fromURL(url).build();
            ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
            collector.CollectData();
        });
    }
}

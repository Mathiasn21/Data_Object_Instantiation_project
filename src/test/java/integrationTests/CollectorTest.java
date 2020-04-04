package integrationTests;

import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class CollectorTest {
    @Test
    void json_simple_data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();
    }

    @Test
    void json_array_data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/testingJSONFile.json" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();
    }

    @Test
    void json_primitive_data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/primitiveJSONtypes.json" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();
    }


    @Test
    void csv_data_from_single_column() throws IOException {
        String path = System.getProperty("user.dir") + "/files/trumpSpeeches.txt" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.setDelimiter("\\P{Alpha}+");
        csvHandler.isSingleColumn(true);
        csvHandler.setSkipEmptyLines(true);

        ICollector collector = Collector.newCollector(resource, csvHandler).build();
        collector.setCompression(true);
        collector.collectData();
    }

    @Test
    void data_from_URL() {
        //TODO: implement correct url for an json api

        fail();
        /*
        Assertions.assertDoesNotThrow(() -> {
            URL url = new URL("http://example.com");
            Resource resource = Resource.newResource().fromURL(url).build();
            ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
            collector.collectData();
        });
        */
    }
}

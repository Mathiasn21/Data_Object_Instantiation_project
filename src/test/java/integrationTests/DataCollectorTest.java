package integrationTests;

import doiframework.core.collectors.DataCollector;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.CSVHandler;
import doiframework.utilities.handlers.JSONHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class DataCollectorTest {
    @Test
    void json_simple_data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataCollector collector = DataCollector.newCollector(dataSource, new JSONHandler()).build();
        collector.collectData();
    }

    @Test
    void json_array_data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/testingJSONFile.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataCollector collector = DataCollector.newCollector(dataSource, new JSONHandler()).build();
        collector.collectData();
    }

    @Test
    void json_primitive_data_from_file() throws IOException {
        String path = System.getProperty("user.dir") + "/files/primitiveJSONtypes.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataCollector collector = DataCollector.newCollector(dataSource, new JSONHandler()).build();
        collector.collectData();
    }


    @Test
    void csv_data_from_single_column() throws IOException {
        String path = System.getProperty("user.dir") + "/files/trumpSpeeches.txt" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.setDelimiter("\\P{Alpha}+");
        csvHandler.isSingleColumn(true);
        csvHandler.skipEmptyLines(true);

        IDataCollector collector = DataCollector.newCollector(dataSource, csvHandler).build();
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
            DataSource resource = DataSource.newResource().fromURL(url).build();
            IDataCollector collector = DataCollector.newCollector(resource, new JSONHandler()).build();
            collector.collectData();
        });
        */
    }
}

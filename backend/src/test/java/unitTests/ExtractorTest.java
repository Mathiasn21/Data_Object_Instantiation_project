package unitTests;

import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.extractors.Extractor;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExtractorTest {
    @Test
    void single_column() throws IOException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("\"dwada\"");
        }

        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        var column1 = extractor.extractColumnFrom("string");

        assertFalse(column1.isEmpty());
        assertEquals(list, column1);

        int i = 0;
        while (i < column1.size()) {
            assertTrue(column1.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void extract_column_from_field(){

    }

    @Test
    void extract_column_from_method(){

    }

    @Test
    void extract_column_from_multiple_fields(){

    }

    @Test
    void extract_column_from_multiple_columns(){

    }

    @Test
    void extract_column_from_multiple_methods(){

    }

    @NotNull
    private ICollector genCollector() throws IOException {
        String path = System.getProperty("user.dir") + "/files/simpleCSV.csv" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        IHandle handler = new CSVHandler();
        ICollector collector = Collector.newCollector(resource, handler).build();
        collector.collectData();
        return collector;
    }
}

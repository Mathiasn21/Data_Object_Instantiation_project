package unitTests;

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

        ICollector collector = genCollector();
        IExtractor extractor = new Extractor<>(collector);
        List<Object> column1 = extractor.extractColumnFrom("Column1");

        assertFalse(column1.isEmpty());
        assertEquals(list, column1);

        int i = 0;
        while (i < column1.size()) {
            assertTrue(column1.get(i) instanceof String);
            i++;
        }
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

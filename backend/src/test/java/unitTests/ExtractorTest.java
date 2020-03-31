package unitTests;

import DTOs.ComplexDTOCSV;
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
    void single_column() throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("\"dwada\"");
        }

        ICollector collector = genCollector();
        IExtractor extractor = new Extractor<>(collector);
        Class<?>[] types = collector.getPrimaryKeyTypes();
        List<Object> column1 = extractor.extractColumnFrom("Column1");

        assertFalse(column1.isEmpty());
        assertEquals(list, column1);

        int i = 0;
        while (i < column1.size()) {
            assertTrue(column1.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void all_columns() throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add(new ComplexDTOCSV("\"dwada\"", 5.5, 5));
        }

        ICollector collector = genCollector();
        IExtractor extractor = new Extractor<>(collector);
        List<Object[]> res = extractor.extractColumns("2");

        assertFalse(res.isEmpty());
        int i = 0;
        while (i < res.size()) {
            assertTrue(res.get(i)[0] instanceof String);
            assertTrue(res.get(i)[1] instanceof Double);
            assertTrue(res.get(i)[2] instanceof Integer);
            i++;
        }
    }

    @Test
    void all_columns_as_type_T() throws IOException, NoSuchFieldException, IllegalAccessException {
        List<ComplexDTOCSV> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add(new ComplexDTOCSV("\"dwada\"", 5.5, 5));
        }

        ICollector collector = genCollector();
        IExtractor extractor = new Extractor<>(collector);
        Class<?>[] types = collector.getPrimaryKeyTypes();
        Class<?> clazz = collector.getClazz();

        List<Object[]> res = extractor.extractColumns(clazz);
        assertFalse(res.isEmpty());
        int i = 0;
        while (i < res.size()) {
            assertTrue(res.get(i)[0] instanceof String);
            assertTrue(res.get(i)[1] instanceof Double);
            assertTrue(res.get(i)[2] instanceof Integer);
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

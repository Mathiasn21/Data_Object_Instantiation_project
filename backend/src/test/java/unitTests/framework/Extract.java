package unitTests.framework;

import DTOs.ComplexDTOCSV;
import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.extractors.Extractor;
import framework.extractors.IExtractor;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extract {
    @Test
    void single_column() throws IOException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("dwada");
        }

        ICollector collector = genCollector();
        IExtractor extractor = new Extractor<>(collector);
        Class<?>[] types = collector.getPrimaryKeyTypes();
        List<Object> column1 = extractor.extractColumnFrom("Column1");

        Assertions.assertFalse(column1.isEmpty());
        Assertions.assertEquals(list, column1);

        int i = 0;
        while (i < column1.size()) {
            Assertions.assertTrue(column1.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void several_columns() throws IOException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add(new ComplexDTOCSV("dwada", 5.5, 5));
        }

        ICollector collector = genCollector();
        IExtractor extractor = new Extractor<>(collector);
        Class<?>[] types = collector.getPrimaryKeyTypes();

        List<Object> res = extractor.extractAllColumns();

        Assertions.assertFalse(res.isEmpty());
        Assertions.assertEquals(list, res);

        int i = 0;
        while (i < res.size()) {
            Assertions.assertTrue(res.get(i) instanceof ComplexDTOCSV);
            i++;
        }
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

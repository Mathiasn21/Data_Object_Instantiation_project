package unitTests;

import DTOs.ComplexDTOCSV;
import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.extractors.Extractor;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class ExtractorTest {
    @Test
    void single_column_by_string() throws IOException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("\"dwada\"");
        }

        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        var column = extractor.extractColumnFrom("string");

        assertFalse(column.isEmpty());
        assertEquals(list, column);

        int i = 0;
        while (i < column.size()) {
            assertTrue(column.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void single_column_by_field() throws IOException, IllegalAccessException, NoSuchFieldException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("\"dwada\"");
        }

        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        Class<ComplexDTOCSV> clazz = ComplexDTOCSV.class;
        var column = extractor.extractColumnFrom(clazz.getField("string"));

        assertFalse(column.isEmpty());
        assertEquals(list, column);

        int i = 0;
        while (i < column.size()) {
            assertTrue(column.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void single_column_by_method() throws IOException, IllegalAccessException, NoSuchMethodException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("\"dwada\"");
        }

        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        Class<ComplexDTOCSV> clazz = ComplexDTOCSV.class;
        var column = extractor.extractColumnFrom(clazz.getMethod("getString"));

        assertFalse(column.isEmpty());
        assertEquals(list, column);

        int i = 0;
        while (i < column.size()) {
            assertTrue(column.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void multiple_columns_by_fields() throws IOException, IllegalAccessException, NoSuchFieldException {
        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        Class<ComplexDTOCSV> clazz = ComplexDTOCSV.class;
        Field[] fields = {clazz.getField("string"), clazz.getField("doubles"), clazz.getField("integer")};
        var columnMap = extractor.extractColumns(fields);
           assertFalse(columnMap.isEmpty());

        Class<?>[] instance = {String.class, Double.class, Integer.class};
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            var objectList = columnMap.get(field);
            assertFalse(objectList.isEmpty());

            int j = 0;
            while (j < objectList.size()) {
                assertSame(objectList.get(j).getClass(), instance[i]);
                j++;
            }
        }
    }

    @Test
    void multiple_columns_by_methods() throws IOException, IllegalAccessException, NoSuchMethodException {
        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        Class<ComplexDTOCSV> clazz = ComplexDTOCSV.class;
        Method[] methods = {clazz.getMethod("getString"), clazz.getMethod("getDoubles"), clazz.getMethod("getInteger")};
        var columnMap = extractor.extractColumns(methods);
        assertFalse(columnMap.isEmpty());

        Class<?>[] instance = {String.class, Double.class, Integer.class};
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            var objectList = columnMap.get(method);
            assertFalse(objectList.isEmpty());

            int j = 0;
            while (j < objectList.size()) {
                assertSame(objectList.get(j).getClass(), instance[i]);
                j++;
            }
        }
    }


    @Test
    void multiple_columns_by_strings() throws IOException, IllegalAccessException {
        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        String[] strings = {"string", "Doubles", "integer"};
        var columnMap = extractor.extractColumns(strings);
        assertFalse(columnMap.isEmpty());

        Class<?>[] instance = {String.class, Double.class, Integer.class};
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];

            var objectList = columnMap.get(string);
            assertFalse(objectList.isEmpty());

            int j = 0;
            while (j < objectList.size()) {
                assertSame(objectList.get(j).getClass(), instance[i]);
                j++;
            }
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

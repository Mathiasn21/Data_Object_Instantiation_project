package integrationTests;

import DTOs.ComplexDTOCSV;
import doiframework.core.collectors.Collector;
import doiframework.core.collectors.ICollector;
import doiframework.exceptions.NoSuchColumnException;
import doiframework.core.extractors.Extractor;
import doiframework.core.resource.Resource;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.utilities.handlers.CSVHandler;
import doiframework.utilities.handlers.IHandle;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class ExtractorTest {
    @Test
    void single_column_by_string() throws IOException, NoSuchColumnException {
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
    void single_column_by_field() throws IOException, NoSuchFieldException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("\"dwada\"");
        }

        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        var clazz = ComplexDTOCSV.class;
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
    void single_column_by_method() throws IOException, NoSuchMethodException, NoSuchColumnException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            list.add("\"dwada\"");
        }

        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        var clazz = ComplexDTOCSV.class;
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
    void multiple_columns_by_fields() throws IOException, NoSuchFieldException, NotPrimitiveNumberException {
        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        var clazz = ComplexDTOCSV.class;
        var fields = Arrays.asList(clazz.getField("string"), clazz.getField("doubles"), clazz.getField("integer"));
        var columnMap = extractor.extractColumnsUsingFields(fields);
        assertFalse(columnMap.isEmpty());

        System.out.println(extractor.extractReportUsingFields(fields).values());

        Class<?>[] instance = {String.class, Double.class, Integer.class};
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);

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
    void multiple_columns_by_methods() throws IOException, NoSuchMethodException, NoSuchColumnException, NotPrimitiveNumberException {
        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        var clazz = ComplexDTOCSV.class;
        var methods = Arrays.asList(clazz.getMethod("getString"), clazz.getMethod("getDoubles"), clazz.getMethod("getInteger"));
        Map<String, Map<String, Double>> report = extractor.extractReportUsingMethods(methods);
        var columnMap = extractor.extractColumnsUsingMethods(methods);
        assertFalse(columnMap.isEmpty());

        Class<?>[] instance = {String.class, Double.class, Integer.class};
        for (int i = 0; i < methods.size(); i++) {
            Method method = methods.get(i);

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
    void multiple_columns_by_strings() throws IOException, NoSuchColumnException {
        var collector = genCollector();
        var extractor = new Extractor<>(collector);
        var strings = Arrays.asList("string", "Doubles", "integer");
        var columnMap = extractor.extractColumnsUsingStrings(strings);
        assertFalse(columnMap.isEmpty());

        Class<?>[] instance = {String.class, Double.class, Integer.class};
        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);

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

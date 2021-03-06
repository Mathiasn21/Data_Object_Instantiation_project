package integrationTests;

import doiframework.core.collectors.DataCollector;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.extractors.DataExtractor;
import doiframework.exceptions.NoSuchColumnException;
import doiframework.core.resource.DataSource;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.utilities.handlers.CSVHandler;
import doiframework.utilities.handlers.IDataHandler;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import DTOs.ShowAPIDTO;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class DataExtractorTest {
    @Test
    void single_column_by_string() throws IOException, NoSuchColumnException {
        var collector = genCollector();
        var extractor = new DataExtractor<>(collector);
        var column = extractor.extractColumnFrom("string");

        assertFalse(column.isEmpty());

        int i = 0;
        while (i < column.size()) {
            assertTrue(column.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void single_column_by_field() throws IOException, NoSuchFieldException {
        var collector = genCollector();
        var extractor = new DataExtractor<>(collector);
        var clazz = ShowAPIDTO.class;
        var column = extractor.extractColumnFrom(clazz.getField("string"));

        assertFalse(column.isEmpty());

        int i = 0;
        while (i < column.size()) {
            assertTrue(column.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void single_column_by_method() throws IOException, NoSuchMethodException, NoSuchColumnException {
        var collector = genCollector();
        var extractor = new DataExtractor<>(collector);
        var clazz = ShowAPIDTO.class;
        var column = extractor.extractColumnFrom(clazz.getMethod("getString"));

        assertFalse(column.isEmpty());

        int i = 0;
        while (i < column.size()) {
            assertTrue(column.get(i) instanceof String);
            i++;
        }
    }

    @Test
    void multiple_columns_by_fields() throws IOException, NoSuchFieldException, NotPrimitiveNumberException {
        var collector = genCollector();
        var extractor = new DataExtractor<>(collector);
        var clazz = ShowAPIDTO.class;
        var fields = Arrays.asList(clazz.getField("string"), clazz.getField("aDouble"), clazz.getField("anInt"));
        var columnMap = extractor.extractColumnsUsingFields(fields);
        assertFalse(columnMap.isEmpty());

        System.out.println(extractor.createReportUsingFields(fields).values());

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
        var extractor = new DataExtractor<>(collector);
        var clazz = ShowAPIDTO.class;
        var methods = Arrays.asList(clazz.getMethod("getString"), clazz.getMethod("getaDouble"), clazz.getMethod("getAnInt"));
        var columnMap = extractor.extractColumnsUsingMethods(methods);
        Map<String, Map<String, Double>> report = extractor.createReportUsingMethods(methods);
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
        var extractor = new DataExtractor<>(collector);
        var strings = Arrays.asList("string", "aDouble", "anInt");
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

    @Test
    void all_columns_using_fields() throws IOException, ReflectiveOperationException {
        var collector = genCollector();
        var clazz = ShowAPIDTO.class;
        var fields = Arrays.asList(clazz.getField("string"), clazz.getField("aDouble"), clazz.getField("anInt"));
        var extractor = new DataExtractor<>(collector);
        var columnMap = extractor.extractColumnsUsingFields();

        columnMap.values().forEach(System.out::println);

        assertFalse(columnMap.isEmpty());

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
    void all_columns_using_methods() throws IOException, NoSuchMethodException, NoSuchColumnException {
        var collector = genCollector();
        var clazz = ShowAPIDTO.class;
        var methods = Arrays.asList(clazz.getMethod("getString"), clazz.getMethod("getaDouble"), clazz.getMethod("getAnInt"));
        var extractor = new DataExtractor<>(collector);
        var columnMap = extractor.extractColumnsUsingMethods();
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

    @NotNull
    private IDataCollector genCollector() throws IOException {
        String path = System.getProperty("user.dir") + "/files/showcaseAPI.csv" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataHandler handler = new CSVHandler();
        IDataCollector collector = DataCollector.newCollector(dataSource, handler).build();
        collector.collectData();
        return collector;
    }
}

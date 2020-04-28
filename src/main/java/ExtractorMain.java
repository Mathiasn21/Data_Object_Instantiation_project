import doiframework.core.collectors.DataCollector;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.extractors.DataExtractor;
import doiframework.core.resource.DataSource;
import doiframework.exceptions.NoSuchColumnException;
import doiframework.utilities.handlers.CSVHandler;
import doiframework.utilities.handlers.IDataHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;

public class ExtractorMain {
    public static void main(String[] args) throws NoSuchColumnException, IOException, NoSuchMethodException, NoSuchFieldException {
        var collector = genCollector();
        var extractor = new DataExtractor<>(collector);
        var strings = Arrays.asList("string", "anInt", "aDouble");
        var columnsUsingStrings = extractor.extractColumnsUsingStrings(strings);
        columnsUsingStrings.values().forEach(System.out::println);
        System.out.println("\n");

        Class<?> clazz = extractor.getDataObjectClass();

        var methods = Arrays.asList(clazz.getMethod("getString"), clazz.getMethod("getAnInt"), clazz.getMethod("getaDouble"));
        var columnsUsingMethodsMap = extractor.extractColumnsUsingMethods(methods);
        columnsUsingMethodsMap.values().forEach(System.out::println);
        System.out.println("\n");

        var fields = Arrays.asList(clazz.getField("string"), clazz.getField("anInt"), clazz.getField("aDouble"));
        var columnsUsingFieldsMap = extractor.extractColumnsUsingFields(fields);
        //Alternative by only utilizing a single field
        // Class<FinalCountdownDTO> clazz = FinalCountdownDTO.class;

        var columnUsingField = extractor.extractColumnFrom(clazz.getField("lyrics"));
        columnsUsingFieldsMap.values().forEach(System.out::println);
        System.out.println("\n");
    }


    @NotNull
    private static IDataCollector genCollector() throws IOException {
        String path = System.getProperty("user.dir") + "/files/showcaseAPI.csv" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataHandler handler = new CSVHandler();
        IDataCollector collector = DataCollector.newCollector(dataSource, handler).build();
        collector.collectData();
        return collector;
    }
}

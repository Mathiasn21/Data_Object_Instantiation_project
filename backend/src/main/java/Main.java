import framework.collectors.CSVCollector;
import framework.collectors.ICollector;
import framework.collectors.JSONCollector;
import framework.collectors.Setting;
import framework.utilities.Search;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        testCollectors();
    }

    private static void testCollectors() throws IOException {
        ICollector collector = new CSVCollector("testingFileCSV.csv");
        collector.setSetting(Setting.DELIMITER, ";");
        collector.loadAndReadFile();

        System.out.println(collector);
        System.out.println(Arrays.toString(collector.getAllPrimaryColumns()));
        String[][] columns = collector.getAllColumns();
        for(String[] column : columns){
            if(column.length == 0)continue;
            System.out.println(Arrays.toString(column));
        }

        System.out.println("\n\n");
        String[] primaryKeys = {"Institusjonskode", "Institusjonsnavn"};
        ICollector collector2 = new JSONCollector("20200210-123-Registrerte studenter.json", primaryKeys);
        collector2.loadAndReadFile();

        String primaryJSONColumns = Arrays.toString(collector2.getAllPrimaryColumns());
        String[][] primaryColumns = collector2.getAllColumns();

        for(String[] column : primaryColumns){
            if(column.length == 0)continue;
            System.out.println(Arrays.toString(column));
        }
        System.out.println(primaryJSONColumns);
    }
}

import framework.dataCollection.CSVCollector;
import framework.dataCollection.ICollector;
import framework.dataCollection.Setting;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
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
    }
}

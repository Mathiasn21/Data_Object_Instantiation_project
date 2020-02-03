package framework.dataCollection;

import java.util.Collection;

/**
 * Class for collecting data given a single file
 */
public class CSVCollector extends Collector{
    private final String fileName;
    private final String delimiter;
    private String columns;


    public CSVCollector(String fileName) {
        this(fileName, ",");
    }

    public CSVCollector(String fileName, String delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
    }

    @Override
    public String toString() {
        return "CSVCollector";
    }
}

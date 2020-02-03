package framework.dataCollection;

/**
 * Class for collecting data given a single file
 */
public class CSVCollector implements ICollector{
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

    public void getAllPrimaryColumns() {

    }

    public void getAllFilledColumns() {

    }

    public void getCategoryBy(String name) {

    }


    @Override
    public String toString() {
        return "CSVCollector";
    }
}

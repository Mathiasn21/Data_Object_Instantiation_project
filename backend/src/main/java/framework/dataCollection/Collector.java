package framework.dataCollection;

/**
 * Class for collecting data given a single file
 */
public class Collector implements ICollector{
    private final String fileName;
    private final String format;


    public Collector(String fileName) {
        this(fileName, FILE_FORMAT);
    }

    public Collector(String fileName, String format) {
        this.fileName = fileName;
        this.format = format;
    }

    public void getAllPrimaryColumns() {


    }

    public void getAllFilledColumns() {

    }

    public void getCategoryBy(String name) {

    }


    @Override
    public String toString() {
        return "Collector";
    }
}

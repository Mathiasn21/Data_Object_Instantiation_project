package framework.dataCollection;

import framework.HandleStorage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class for collecting data given a single file
 */
public class CSVCollector extends Collector{
    private final String fileName;
    private final String delimiter;
    private Collection<String[]> columns;
    private Collection<String[]> rows;


    public CSVCollector(String fileName) {
        this(fileName, ",");
    }

    public CSVCollector(String fileName, String delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
        String line;
    }

    @NotNull
    @Contract(pure = true)
    private String[] splitLineOn(@NotNull String line){
        return line.split(delimiter);
    }

    @Override
    public String toString() {
        return "CSVCollector";
    }

    @Override
    public void loadAndReadFile(String fileName) throws IOException {
        String line;
        BufferedReader bufferedReader = HandleStorage.readFromFile(fileName);
        Collection<String[]> rows = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            rows.add(splitLineOn(line));
        }
        this.rows = rows;
    }

    @Override
    public void loadAndReadFile(File file) {

    }
}

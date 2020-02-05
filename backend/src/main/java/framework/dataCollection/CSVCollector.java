package framework.dataCollection;

import framework.HandleStorage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Class for collecting data given a single file
 */
public class CSVCollector extends Collector{
    private final String fileName;
    private final String delimiter;
    private Collection<String[]> rows;


    public CSVCollector(String fileName) {
        this(fileName, ",");
    }

    public CSVCollector(String fileName, String delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
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
        boolean foundPrimarycolumns = false;
        while ((line = bufferedReader.readLine()) != null) {
            String[] r = splitLineOn(line);
            if(!foundPrimarycolumns && calcPRowContainsPrimaryColumns(r)){
                setPrimaryColumns(r);
                foundPrimarycolumns = true;
                continue;
            }
            rows.add(r);
        }
        this.rows = rows;
    }

    @Override
    public void loadAndReadFile(File file) {

    }

    @Contract(pure = true)
    private static boolean calcPRowContainsPrimaryColumns(@NotNull String[] row){
        //TODO: Delegate settings to config file
        //TODO: Change out this method with one that utilizes probability instead
        String isNumber = "^-?\\d*\\.{0,1}\\d+$";
        final Pattern digitPattern = Pattern.compile(isNumber, Pattern.MULTILINE);
        for(String str : row) {
            if (digitPattern.matcher(isNumber).find() || str.isBlank()) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns the a index or -1 if none was found
     * @param column String
     * @return int
     */
    @Contract(pure = true)
    private int findColumnIndex(String column) {
        String[] primaryColumns = getAllPrimaryColumns();
        int result = -1;
        for (int i = 0, primaryColumnsLength = primaryColumns.length; i < primaryColumnsLength; i++) {
            String primaryColumn = primaryColumns[i];
            if (primaryColumn.equals(column)) {
                result = i;
                break;
            }
        }
        return result;
    }
}

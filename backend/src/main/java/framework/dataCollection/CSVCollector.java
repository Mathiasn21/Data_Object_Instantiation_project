package framework.dataCollection;

import framework.HandleStorage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/** Class for collecting data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class CSVCollector extends Collector {
    private final String fileName;
    private final static Map<Setting, String> settings = new HashMap<>();
    private List<String[]> informationalRows = new ArrayList<>();
    private List<String[]> rows;

    //Initiating default settings
    static {
        settings.put(Setting.DELIMITER, ",");
    }

    public CSVCollector(@NotNull File file) {
        this(file.getName());
    }

    public CSVCollector(@NotNull String fileName) {
        this.fileName = fileName;
        initialize();
    }


    private void initialize() {
        setAllSettings(settings);
    }

    @NotNull
    @Contract(pure = true)
    private String[] splitLineOn(@NotNull String line){
        String delimiter = getSetting(Setting.DELIMITER);
        return line.split(delimiter);
    }

    @Override
    public String toString() {
        return "CSVCollector";
    }

    @Override
    public String[][] getAllColumns() {
        String[] primaryColumns = getAllPrimaryColumns();
        int columnLength = primaryColumns.length;
        int rowLength = rows.size();
        String[][] columns = new String[rowLength][columnLength];
        columns[0] = primaryColumns;
        for(int row = 1; row < rowLength; row++){
            columns[row] = rows.get(row);
        }
        return columns;
    }

    public List<String[]> getInformationalRows() {
        return informationalRows;
    }

    /**
     * @param name String
     * @return
     */
    @Override
    public Item[] getCategoryBy(String name) {
        return new Item[0];
    }

    @Override
    public void loadAndReadFile() throws IOException {
        String line;
        BufferedReader bufferedReader = HandleStorage.readFromFile(fileName);
        List<String[]> rows = new ArrayList<>();
        boolean foundPrimarycolumns = false;
        while ((line = bufferedReader.readLine()) != null) {
            String[] r = splitLineOn(line);
            if(!foundPrimarycolumns && calcPRowContainsPrimaryColumns(r)){
                setPrimaryColumns(r);
                foundPrimarycolumns = true;
                continue;
            }else if(!foundPrimarycolumns){
                informationalRows.add(r);
                continue;
            }
            rows.add(r);
        }
        this.rows = rows;
    }


    @Contract(pure = true)
    private static boolean calcPRowContainsPrimaryColumns(@NotNull String[] row){
        //TODO: Delegate settings to config file
        //TODO: Change out this method with one that utilizes probability instead

        if(row.length == 0){return false;}
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

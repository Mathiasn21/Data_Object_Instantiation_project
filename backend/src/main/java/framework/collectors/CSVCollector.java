package framework.collectors;

import framework.annotations.DataObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/** Class for collecting data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class CSVCollector extends Collector implements ICSV {
    private String fileName;
    private final static Map<Setting, String> settings = new HashMap<>();
    private List<String[]> rows;

    //Initiating default settings
    static {
        settings.put(Setting.DELIMITER, ",");
    }

    public CSVCollector(){
        initialize();
        fileName = null;
    }

    public CSVCollector(@NotNull File file) {
        this(file.getName());
    }

    /**
     * @param fileName String
     */
    public CSVCollector(@NotNull String fileName) {
        this.fileName = fileName;
        initialize();
    }

    /**
     * @param fileName String
     * @param primaryColumns String[]
     */
    public CSVCollector(@NotNull String fileName, @NotNull List<DataObject> primaryColumns){
        this(fileName);
        setPrimaryColumns(primaryColumns);
    }

    /**
     * @param file {@link File}
     * @param primaryColumns String[]
     */
    public CSVCollector(@NotNull File file, @NotNull List<DataObject> primaryColumns){
        this(file.getName(), primaryColumns);
    }


    private void initialize() {
        setAllSettings(settings);
    }

    /**
     * @param line String
     * @return String[]
     */
    @NotNull
    @Contract(pure = true)
    private String[] splitLineOn(@NotNull String line){
        String delimiter = getSetting(Setting.DELIMITER);
        return line.split(delimiter);
    }

    @Override
    public void loadAndReadFile() throws IOException {
        /*String line;
        BufferedReader bufferedReader = ReadFile.from(fileName);
        List<String[]> rows = new ArrayList<>();
        boolean foundPrimarycolumns = false;
        while ((line = bufferedReader.readLine()) != null) {
            String[] r = splitLineOn(line);
            if(!foundPrimarycolumns && calcPRowContainsPrimaryColumns(r)){
                //TODO: fix
                setPrimaryColumns(r);
                foundPrimarycolumns = true;
                continue;
            }else if(!foundPrimarycolumns){
                //TODO: call super method
                super.getInformationalRows().add(r);
                continue;
            }
            rows.add(r);
        }
        this.rows = rows;

         */
    }

    @Override
    public void setDelimiter(String delimiter) {
        //TODO: Implement method
    }


    /**
     * @param row String[]
     * @return boolean
     */
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
     * @return String
     */
    @Override
    public String toString() {
        return "CSVCollector";
    }

}

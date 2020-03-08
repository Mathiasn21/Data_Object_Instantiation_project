package framework.utilities.data.handle;

import framework.utilities.data.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CSVHandler implements IHandle{
    private String delimiter = ",";
    private Class<?>[] primaryKeyTypes;
    private String[] primaryKeys;

    @Override
    public void setPrimaryKeyTypes(Class<?>[] types) {
        primaryKeyTypes = types;
    }

    @Override
    public void setPrimaryKeys(String[] keys) {
        primaryKeys = keys;
    }

    @Override
    @NotNull
    public List<List<Object>> handle(@NotNull BufferedReader bufferedReader) throws IOException {
        String line;
        List<List<Object>> rows = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] r = splitLineOn(line);
            List<Object> args = new ArrayList<>();

            for(int i = 0; i < r.length; i++){
                args.add(Parser.parseValueToObject(primaryKeyTypes[i], r[i]));
            }
            rows.add(args);
            //TODO: Add logic for detecting the first full row -> primaryRow without knowing details about said keys
        }
        return rows;
    }

    public void setColumns(){

    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * @param line String
     * @return String[]
     */
    @NotNull
    @Contract(pure = true)
    private String[] splitLineOn(@NotNull String line){
        return line.split(delimiter);
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
}

package framework.utilities.data.handle;

import framework.utilities.data.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/** Class that contains concrete instruction for handling CSV data.
 *  By default it utilizes the delimiter ","
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class CSVHandler implements IHandle{
    private String delimiter = ",";
    private Class<?>[] primaryKeyTypes;
    private String[] primaryKeys;

    /**
     * @param types {@link Class}&lt;?&gt;[]
     */
    @Override
    public final void setPrimaryKeyTypes(@NotNull Class<?>[] types) {
        primaryKeyTypes = types;
    }

    /**
     * @param keys String[]
     */
    @Override
    public final void setPrimaryKeys(@NotNull String[] keys) {
        primaryKeys = keys;
    }

    /**
     * @param delimiter String
     */
    public final void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * @param bufferedReader {@link BufferedReader}
     * @return {@link List}&lt;{@link List}&lt;{@link Object}&gt;&gt;
     * @throws IOException IOException
     */
    @Override
    public final @NotNull List<Object[]> handle(@NotNull BufferedReader bufferedReader) throws IOException {
        String line;
        List<Object[]> rows = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] r = splitLineOn(line);
            List<Object> args = new ArrayList<>();

            for(int i = 0; i < r.length; i++){
                args.add(Parser.parseValueToObject(primaryKeyTypes[i], r[i]));
            }
            rows.add(args.toArray());
            //TODO: Add logic for detecting the first full row -> primaryRow without knowing details about said keys
        }
        return rows;
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

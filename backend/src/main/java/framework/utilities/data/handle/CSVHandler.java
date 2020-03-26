package framework.utilities.data.handle;

import framework.utilities.data.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.math.NumberUtils.createNumber;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

/** Class that contains concrete instruction for handling JSON data.
 *  By default it utilizes the delimiter ","
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class CSVHandler implements IHandle{
    private char delimiter = ',';
    private boolean isSingleColumn = false;

    private Class<?>[] primaryKeyTypes;
    private String[] primaryKeys;
    private Map<String, Boolean> settings = new HashMap<>();
    private boolean skipEmptyLines = false;
    private boolean convertFloatToDouble = true;

    /**
     * @param types {@link Class}&lt;?&gt;[]
     */
    @Override
    public final void setPrimaryKeyTypes(@NotNull Class<?>[] types) { primaryKeyTypes = types; }

    /**
     * @param keys String[]
     */
    @Override
    public final void setPrimaryKeys(@NotNull String[] keys) { primaryKeys = keys; }

    /**
     * A char to split each column for each row
     * @param delimiter String
     */
    public final void setDelimiter(char delimiter) { this.delimiter = delimiter; }

    /**
     * Decides whether or not to treat each all data
     * as a single column or several.
     * @param singleColumn boolean
     */
    public void isSingleColumn(boolean singleColumn) { isSingleColumn = singleColumn; }

    /**
     * Changes the setting for auto converting
     * {@link Class}&lt;{@link Float}&gt; to {@link Class}&lt;{@link Double}&gt;.
     * Change this if you're utilizing floats
     * in a dataobject constructor.
     * @param convertFloatToDouble boolean
     */
    public void isConvertintFloatToDouble(boolean convertFloatToDouble) {
        this.convertFloatToDouble = convertFloatToDouble;
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
        boolean foundTypes = false;

        List<Class<?>> types = new ArrayList<>(1);
        while ((line = bufferedReader.readLine()) != null) {
            if(skipEmptyLines && (line.isEmpty() || line.isBlank())){ continue; }

            List<Object> args = new ArrayList<>();
            String[] r = splitLineOn(line);

            //Find primary types if not already
            if(!foundTypes){
                if(isSingleColumn){
                    Class<?> type = r.length > 1 ? singleColumnPrimitiveTypeFrom(r[0], r[1]) : primitiveTypeFrom(r[0]);
                    types.add(type);
                }else{ types = findAllPrimitiveTypes(r); }
                foundTypes = true;
            }

            for (int i = 0; i < r.length; i++) {
                String value = r[i];

                //Add args to row array and create a new ArrayList if theres only one column
                if (isSingleColumn) {
                    args.add(Parser.classToValueFromObject(types.get(0), value));
                    rows.add(args.toArray());
                    args = new ArrayList<>();
                    continue;
                }
                args.add(Parser.classToValueFromObject(types.get(i), value));
            }
            //If it's multiple column, append thoose to the row
            if(!isSingleColumn){
                rows.add(args.toArray());
            }
        }

        //This is pretty much always true, but, better to be safe
        if(primaryKeyTypes == null || primaryKeyTypes.length == 0){
            setPrimaryKeyTypes(types.toArray(Class<?>[]::new));
        }
        return rows;
    }

    /**
     * Returns all found types from a given string.
     * Does not handle arrays or other complex objects.
     * @param r String[]
     * @return {@link List}&lt;{@link Class}&lt;?&gt;&gt;
     */
    @NotNull
    @Contract(pure = true)
    private List<Class<?>> findAllPrimitiveTypes(@NotNull String[] r) {
        List<Class<?>> types = new ArrayList<>(r.length);
        for (String str : r) {
            types.add(primitiveTypeFrom(str));
        }
        return types;
    }

    /**
     * @param line String
     * @return String[]
     */
    @NotNull
    @Contract(pure = true)
    private String[] splitLineOn(@NotNull String line){
        return line.split(String.valueOf(delimiter));
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

    private Class<?> primitiveTypeFrom(@NotNull String element) {
        if(isCreatable(element)){
            Number number = createNumber(element);
            Class<?> clazz = number.getClass();
            return clazz == Float.class && convertFloatToDouble ? Double.class : clazz;
        }
        return String.class;
    }

    private Class<?> singleColumnPrimitiveTypeFrom(@NotNull String firstElement, @NotNull String secondElement) {
        if((isCreatable(firstElement) && isCreatable(secondElement))){
            Number first = createNumber(firstElement);
            Number second = createNumber(firstElement);
            if(first.getClass() != second.getClass()){
                return Double.class;
            }
            return first.getClass();
        }
        return String.class;
    }
}

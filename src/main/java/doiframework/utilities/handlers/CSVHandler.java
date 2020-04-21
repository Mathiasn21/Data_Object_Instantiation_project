package doiframework.utilities.handlers;

import doiframework.core.annotations.DataObject;
import doiframework.utilities.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.createNumber;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

/** Class that contains concrete instruction for handling JSON resource.
 *  By default it utilizes the delimiter ","
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.5.0
 */
public class CSVHandler implements IHandle{
    private String delimiter = ",";
    private Class<?>[] primaryKeyTypes;

    private boolean isSingleColumn = false;
    private boolean skipEmptyLines = false;
    private boolean removeDoubleQuotes = false;
    private boolean removeSingleQuotes = false;
    private boolean convertFloatToDouble = true;
    private boolean sampleEachLine;

    private int skipFirstXLines = 0;
    private int[] skipIndexes = new int[0];

    /**
     * A char to split each column for each row.
     * @param delimiter char
     */
    public final void setDelimiter(char delimiter) { this.delimiter = String.valueOf(delimiter); }

    /**
     * A char to split each column for each row.
     * @param delimiter String
     */
    public final void setDelimiter(String delimiter) { this.delimiter = delimiter; }

    /**Determines whether or not the handler will sample
     * each line for given types. Eg: Try to determine each type, for every line.
     * By default this is false/disabled as it requires additional overhead.
     * @param sampleEachLine boolean
     */
    public final void setSampleEachLine(boolean sampleEachLine) { this.sampleEachLine = sampleEachLine; }


    /**
     * Remove any double quotes inside a string.
     * By default this is off.
     * @param removeDoubleQuotes boolean
     */
    public final void removeDoubleQuotes(boolean removeDoubleQuotes) {
        this.removeDoubleQuotes = removeDoubleQuotes;
    }

    /**
     * Remove any double quotes inside a string.
     * By default this is off.
     * @param removeSingleQuotes boolean
     */
    public final void removeSingleQuotes(boolean removeSingleQuotes) {
        this.removeSingleQuotes = removeSingleQuotes;
    }

    /**
     * @param skipEmptyLines boolean
     */
    public final void skipEmptyLines(boolean skipEmptyLines) {
        this.skipEmptyLines = skipEmptyLines;
    }

    /**
     * Allows one to skip the first lines from the bufferedReader.
     * By default this is 0. Aka: skip none.
     * @param xLines int
     */
    public final void skipFirstXLines(int xLines){
        if(xLines < 0){ return; }
        this.skipFirstXLines = xLines;
    }

    /**
     * Allows one to skip certain indexes for each read line.
     * By default this is -1. Aka: skip none.
     * @param index int
     */
    public final void skipLineIndexes(int index){
        if(index < 0){ return; }
        this.skipIndexes = genRange(index, index + 1);
    }

    /**
     * Allows one to skip certain indexes for each read line.
     * By default this is -1. Aka: skip none.
     * @param indexes int
     */
    public final void skipLineIndexes(@NotNull int ...indexes){
        if(indexes.length == 0){ return; }
        this.skipIndexes = indexes;
    }

    /**
     * Allows one to skip certain indexes for each read line.
     * By default this is null. Aka: skip none.
     * @param fromIndex int
     * @param toIndex int
     */
    public final void skipLineIndexes(int fromIndex, int toIndex){
        if(fromIndex < 0 || toIndex < 0 || fromIndex > toIndex){ return; }
        this.skipIndexes = genRange(fromIndex, toIndex);
        Arrays.sort(skipIndexes);
    }

    /**
     * Decides whether or not to treat each all resource
     * as a single column or several.
     * @param singleColumn boolean
     */
    public final void isSingleColumn(boolean singleColumn) { isSingleColumn = singleColumn; }

    /**
     * Changes the setting for auto converting
     * {@link Class}&lt;{@link Float}&gt; to {@link Class}&lt;{@link Double}&gt;.
     * Change this to false if you're utilizing floats
     * in a {@link DataObject} constructor.
     * @param convertFloatToDouble boolean
     */
    public final void isConvertingFloatToDouble(boolean convertFloatToDouble) {
        this.convertFloatToDouble = convertFloatToDouble;
    }

    /**
     * @param bufferedReader {@link BufferedReader}
     * @return {@link List}&lt;{@link List}&lt;{@link Object}&gt;&gt;
     * @throws IOException IOException
     */
    @Override
    public @NotNull List<Object[]> handle(@NotNull BufferedReader bufferedReader) throws IOException {
        String line;
        List<Object[]> rows = new ArrayList<>();
        boolean foundTypes = false;

        List<Class<?>> types = new ArrayList<>(1);
        while ((line = bufferedReader.readLine()) != null) {
            List<Object> args = new ArrayList<>();

            //Skips lines if this setting is turned on
            if(skipFirstXLines > 0){
                skipFirstXLines--;
                continue;
            }

            //Handle quotes if necessary
            if(removeDoubleQuotes){ line = line.replace("\"", ""); }
            if(removeSingleQuotes){ line = line.replace("'", ""); }

            String[] r = splitLineOn(line);

            //Find primary types if not already
            if(sampleEachLine || !foundTypes){
                if(isSingleColumn){
                    Class<?> type = r.length > 1 ? singleColumnPrimitiveTypeFrom(r[0], r[1]) : primitiveTypeFrom(r[0]);
                    types.add(type);
                }else{ types = findAllPrimitiveTypes(r); }
                foundTypes = true;
            }

            for (int i = 0; i < r.length; i++) {
                String value = r[i];

                if(skipEmptyLines && (value.isEmpty() || value.isBlank())){ continue; }

                //Skips indexes if this has been specified
                if(skipIndexes.length > 0 && Arrays.binarySearch(skipIndexes, i) >= 0){ continue; }

                //Add args to row array and create a new ArrayList if there's only one column
                if (isSingleColumn) {
                    args.add(Parser.toPrimitiveValueGivenType(types.get(0), value));
                    rows.add(args.toArray());
                    args = new ArrayList<>();
                    continue;
                }
                args.add(Parser.toPrimitiveValueGivenType(types.get(i), value));
            }
            //If it's multiple column, append those to the row
            if(!isSingleColumn){ rows.add(args.toArray()); }
        }

        //This is pretty much always true, but, better to be safe
        if(primaryKeyTypes == null || primaryKeyTypes.length == 0){
            primaryKeyTypes = types.toArray(Class<?>[]::new);
        }
        return rows;
    }

    /**
     * Returns all found types from a execute string.
     * Does not handlers arrays or other complex objects.
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
        return line.split(delimiter);
    }

    /**
     * @param element String
     * @return {@link Class}&lt;?&gt;
     */
    private Class<?> primitiveTypeFrom(@NotNull String element) {
        if(isCreatable(element)){
            Number number = createNumber(element);
            Class<?> clazz = number.getClass();
            return clazz == Float.class && convertFloatToDouble ? Double.class : clazz;
        }
        return String.class;
    }

    /**
     * @param firstElement String
     * @param secondElement String
     * @return {@link Class}&lt;?&gt;
     */
    private Class<?> singleColumnPrimitiveTypeFrom(@NotNull String firstElement, @NotNull String secondElement) {
        if((isCreatable(firstElement) && isCreatable(secondElement))){
            Number first = createNumber(firstElement);
            Number second = createNumber(secondElement);

            //In case there's a difference the type of number.
            if(first.getClass() != second.getClass()){
                return Double.class;
            }
            return first.getClass();
        }
        return String.class;
    }

    /**
     * @param from int
     * @param to int
     * @return int[]
     */
    @NotNull
    @Contract(pure = true)
    private static int[] genRange(int from, int to) {
        int[] range = new int[Math.abs(from - to)];
        for (int i = from, j = 0; i < to; i++, j++) {
            range[j] = i;
        }
        return range;
    }
}
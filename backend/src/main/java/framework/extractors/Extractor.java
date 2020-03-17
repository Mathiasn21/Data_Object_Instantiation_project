package framework.extractors;

import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0
 */
public final class Extractor<T extends ICollector> implements IExtractor {

    private final T collector;
    private final List<Object> allColumns;

    public Extractor(@NotNull T collector) {
        this.collector = collector;
        this.allColumns = collector.getAllColumns();
    }

    /**
     * @param columnName String
     * @return T extends {@link ICollector}
     */
    @NotNull
    @Override
    public final List<Object> extractColumnFrom(@NotNull String columnName) {
        List<Object> data = null;
        List<Object> allColumns = collector.getAllColumns();
        //Grab fields from class
        Class<?> clazz = allColumns.get(0).getClass();
        Field[] fields = clazz.getFields();

        System.out.println(fields[1].getName());

        if(fields[1].getName().equals(columnName)){
            data.add(fields[1]);
        }

        /*List<String> keys = collector.getPrimaryKeys();
        System.out.println(keys.get(0)); //IS EMPTY???
        //grab primarykeys if exists from collector.*/

        return data;
    }


    /**
     * @return returns the columns given by the user from the file.
     */
    @NotNull
    public final List<Object> extractColumns(){
        //TODO: Alter method as intellij reports many warnings due to degenerated code
        //TODO: Might be better to remove or move the code to another more appropriate method
        List<Object> data = null;
        List<String> primaryKeys = collector.getPrimaryKeys();
        for(int i = 0; i < primaryKeys.size(); i++){
            for(int j = 0; j < allColumns.size(); j++){
                if (primaryKeys.get(i).toString().equals(allColumns.get(j).toString())){
                    data.add(allColumns.get(j));
                }
            }
        }
        return Collections.unmodifiableList(data);
    }


    /**
     * @return returns all columns from dataset
     */
    @Contract(pure = true)
    @NotNull
    @Override
    public final List<Object> extractAllColumns(){
        return collector.getAllColumns();
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public <O> List<O> extractAllColumns(Class<?> clazz) {
        List<Object> allColumns = collector.getAllColumns();
        Class<?> collectorClazz = collector.getClazz();
        return (List<O>) allColumns;
    }

    /**
     * @param columnName String
     * @return T extends {@link ICollector}
     */
    @Nullable
    @Contract(pure = true)
    @Override
    public final Map<String, Integer> extractReportFrom(@NotNull String columnName) {
        return null;
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    @Override
    public <O> List<O> extractAllColumnsAsT() {
        return (List<O>) collector.getAllColumns();
    }
}

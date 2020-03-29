package framework.extractors;

import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0
 */
public final class Extractor<C extends ICollector> implements IExtractor {
    private final C collector;
    private final List<Object> allColumns;

    public Extractor(@NotNull C collector) {
        this.collector = collector;
        this.allColumns = collector.getAllColumns();
    }

    /**
     * @param columnName String
     * @return T extends {@link ICollector}
     */
    @NotNull
    @Override
    public final List<Object> extractColumnFrom(@NotNull String columnName) throws NoSuchFieldException, IllegalAccessException {
        List<Object> data = null;
        List<Object> allColumns = collector.getAllColumns();
        //Grab fields from class
        //Class<?> clazz = allColumns.get(0).getClass();
        //Field[] fields = clazz.getFields();

        //System.out.println(fields[1].getName());

        for(Object x : allColumns) {
            Class<?> clazz = x.getClass();
            Field field = clazz.getField(columnName);
            assert data != null;
            data.add(field);
        }

        /*List<String> keys = collector.getPrimaryKeys();
        System.out.println(keys.get(0)); //IS EMPTY???
        //grab primarykeys if exists from collector.*/

        return ;
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
    public final Map<String, Double> extractReportFom(@NotNull String columnName) {
        return null;
    }

    @NotNull
    @Override
    public <T> List<T> extractAllColumnsAsT(Class<T> tClass) {
        return new ArrayList<>();
    }
}

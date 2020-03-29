package framework.extractors;

import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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
    public final List<Object> extractColumnFrom(@NotNull String columnName) throws NoSuchFieldException {
        List<Object> data = null;
        List<Object> allColumns = collector.getAllColumns();
        //Grab fields from class
        //Class<?> clazz = allColumns.get(0).getClass();
        //Field[] fields = clazz.getFields();

        //System.out.println(fields[1].getName());

        assert false;
        data.add(Arrays.stream(allColumns.getClass().getDeclaredFields())
                .filter(e -> e.getName().startsWith(columnName))
                .findFirst()
                .map(f -> {
                    f.setAccessible(true);
                    try {
                        return (String) f.get(columnName);
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                }).orElseThrow(IllegalArgumentException::new));

        /*List<String> keys = collector.getPrimaryKeys();
        System.out.println(keys.get(0)); //IS EMPTY???
        //grab primarykeys if exists from collector.*/
        return data;
    }


    /**
     * @return returns all columns from dataset
     */
    @Contract(pure = true)
    @NotNull
    @Override
    public final List<Object[]> extractColumns(){
        return new ArrayList<>();
    }

    @NotNull
    @Contract(pure = true)
    public List<Object[]> extractColumns(@NotNull Class<?> clazz) {
        List<Object> allColumns = collector.getAllColumns();
        Class<?> collectorClazz = collector.getClazz();
        return new ArrayList<>();
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public List<Object[]> extractColumns(@NotNull Field... fields) {
        return null;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public List<Object[]> extractColumns(@NotNull String... columns) {
        return null;
    }

    @Override
    public @NotNull List<Object[]> extractColumns(@NotNull int... indexes) {
        return null;
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

package framework.extractors;

import framework.annotations.DataObject;
import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        List<DataObject> data = new ArrayList<>();
        return Collections.unmodifiableList(data);
    }


    /**
     * @return returns all columns from dataset
     */
    @Contract(pure = true)
    @NotNull
    @Override
    public final List<Object> extractAllColumns(){
        return Collections.unmodifiableList(allColumns);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public <O> List<O> extractAllColumns(Class<?> clazz) {
        return new ArrayList<>();
    }

    /**
     * @param columnName String
     * @return T extends {@link ICollector}
     */
    @Nullable
    @Contract(pure = true)
    @Override
    public final Map<String, Integer> extractReportFom(@NotNull String columnName) {
        return null;
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    @Override
    public <O> List<O> extractAllColumnsAsT() {
        return new ArrayList<>();
    }
}

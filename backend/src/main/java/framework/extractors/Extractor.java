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
        List<Object> data = new ArrayList<>();
        List<Object> allColumns = collector.getAllColumns();
        for (Object allColumn : allColumns) {
            if (allColumn.toString().equals(columnName)) {
                data.add(allColumn);
            }
        }
        return Collections.unmodifiableList(data);
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
        List<O> allColumnsExctacted = null;
        for (Object allColumn : allColumns) {
            allColumnsExctacted.add((O) allColumn);
        }
        return Collections.unmodifiableList(allColumnsExctacted);
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
        List<Object> allColumns = collector.getAllColumns();
        ArrayList<O> allColumnsExctacted = null;
        allColumns.addAll(allColumns);
        return allColumnsExctacted;
    }
}

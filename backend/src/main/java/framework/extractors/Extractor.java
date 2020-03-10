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

    public Extractor(@NotNull T collector) {
        this.collector = collector;
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
     * @return returns the columns given by the user from the file.
     */
    @NotNull
    @Override
    public final List<Object> extractColumnsFrom(){
        //TODO: Alter method as intellij reports many warnings due to degenerated code
        List<DataObject> allColumns = (List<DataObject>) collector.getAllColumns();
        List<DataObject> data = null;
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
    @NotNull
    @Override
    public final Collection<Object> extractAllColumnsFrom(){
        List<DataObject> data = (List<DataObject>) collector.getAllColumns();
        return Collections.unmodifiableList(data);
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
}

package framework.extractors;

import framework.annotations.DataObject;
import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0
 */
public final class Extractor implements IExtractor {
    /**
     * @param collector {@link ICollector}
     * @param columnName String
     * @param <T> T extends {@link ICollector}
     * @return T extends {@link ICollector}
     */
    @NotNull
    @Override
    public final <T extends ICollector> List<DataObject> extractColumnFrom(@NotNull T collector, DataObject columnName) {
        List<DataObject> data = collector.getColumnBy(columnName);
        return Collections.unmodifiableList(data);
    }

    @NotNull
    @Override
    public final <T extends ICollector> List<DataObject> extractGivenColumnsFrom(@NotNull T collector, DataObject columnFrom, DataObject columnTo){
        List<DataObject> dataObjects = null;
        for(int i = 0; i<10; i++){
            dataObjects.add(i, columnFrom);
        }
        return null;
    }

    /**
     * @param collector {@link ICollector}
     * @param <T> T extends {@link ICollector}
     * @return returns all columns from dataset
     */
    @NotNull
    @Override
    public final <T extends  ICollector> Collection<DataObject> extractAllColumnsFrom(@NotNull T collector){
        List<DataObject> data = (List<DataObject>) collector.getAllColumns();
        return Collections.unmodifiableList(data);
    }

    /**
     * @param collector {@link ICollector}
     * @param columnName String
     * @param <T> T extends {@link ICollector}
     * @return T extends {@link ICollector}
     */
    @Nullable
    @Contract(pure = true)
    @Override
    public final <T extends ICollector> Map<String, Integer> extractReportFom(T collector, DataObject columnName) {
        return null;
    }
}

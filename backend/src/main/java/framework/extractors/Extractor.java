package framework.extractors;

import framework.annotations.DataObject;
import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
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
        List<DataObject> data = new ArrayList<>();
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

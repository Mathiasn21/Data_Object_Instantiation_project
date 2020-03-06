package framework.extractors;

import framework.annotations.DataObject;
import framework.collectors.ICollector;
import framework.extractors.IExtractor;
import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/** Class used for extracting information from
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class Extractor implements IExtractor {
    @Override
    public <T extends ICollector> List<DataObject> extractColumnFrom(@NotNull T collector, DataObject columnName) {
        List<DataObject> data = collector.getColumnBy(columnName);
        return Collections.unmodifiableList(data);
    }

    /**
     * @param collector {@link ICollector}
     * @param columnName String
     * @param <T> T extends {@link ICollector}
     * @return T extends {@link ICollector}
     */
    @Override
    public <T extends ICollector> Map<String, Integer> extractReportFom(T collector, DataObject columnName) {
        return null;
    }
}

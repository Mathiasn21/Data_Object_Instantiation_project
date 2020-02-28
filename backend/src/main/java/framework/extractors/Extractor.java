package framework.extractors;

import framework.collectors.ICollector;
import framework.extractors.IExtractor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/** Class used for extracting information from
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class Extractor implements IExtractor {
    @Override
    public <T extends ICollector> double[] extractColumnFrom(@NotNull T collector, String columnName) {
        String[] data = collector.getColumnBy(columnName);
        return new double[0];
    }

    /**
     * @param collector {@link ICollector}
     * @param columnName String
     * @param <T> T extends {@link }
     * @return
     */
    @Override
    public <T extends ICollector> Map<String, Integer> extractReportFom(T collector, String columnName) {
        return null;
    }
}

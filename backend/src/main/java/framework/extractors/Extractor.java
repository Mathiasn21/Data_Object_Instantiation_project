package framework.extractors;

import framework.collectors.ICollector;
import framework.extractors.IExtractor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Extractor implements IExtractor {
    @Override
    public <T extends ICollector> double[] extractColumnFrom(@NotNull T collector, String columnName) {
        String[] data = collector.getColumnBy(columnName);
        return new double[0];
    }

    @Override
    public <T extends ICollector> Map<String, Integer> extractReportFom(T collector, String columnName) {
        return null;
    }
}

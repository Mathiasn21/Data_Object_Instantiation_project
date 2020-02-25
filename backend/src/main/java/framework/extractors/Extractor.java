package framework.extractors;

import framework.collectors.ICollector;
import framework.extractors.IExtractor;
import org.jetbrains.annotations.NotNull;

public class Extractor implements IExtractor {
    @Override
    public <T extends ICollector> double[] extractColumnDataFrom(@NotNull T collector, String columnName) {
        String[] data = collector.getColumnBy(columnName);
        return new double[0];
    }

}

package framework.dataCollection;

public class Extractor implements IExtractor {
    @Override
    public <T extends ICollector> double[] extractColumnDataFrom(T collector, String columnName) {
        String[] data = collector.getColumnBy(columnName);
        return new double[0];
    }
}
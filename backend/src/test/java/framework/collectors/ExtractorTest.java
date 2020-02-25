package framework.collectors;

import framework.extractors.IExtractor;

/** Class for sorting the collected data
 * @author Mathias Walter Nilsen
 * @author Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class ExtractorTest implements IExtractor {
    private final ICollector collector;

    /**
     * @param collector {@link ICollector}
     */
    public ExtractorTest(ICollector collector) {
        this.collector = collector;
    }

    /**
     * @param collector  {@link ICollector}
     * @param columnName String
     * @return double[]
     */
    @Override
    public double[] extractColumnFrom(ICollector collector, String columnName) {
        return new double[0];
    }
}

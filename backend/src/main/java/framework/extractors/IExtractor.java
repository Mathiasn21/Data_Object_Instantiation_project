package framework.extractors;

import framework.collectors.ICollector;

import java.util.Map;

/** Class for extracting primitive data from dataset
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IExtractor{
    /**
     * @param collector {@link ICollector}
     * @param columnName String
     * @param <T> T extends ICollector
     * @return double[]
     */
    <T extends ICollector> double[] extractColumnFrom(T collector, String columnName);

    <T extends ICollector> Map<String, Integer> extractReportFom(T collector, String columnName);

}

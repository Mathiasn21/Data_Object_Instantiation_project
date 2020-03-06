package framework.extractors;

import framework.annotations.DataObject;
import framework.collectors.ICollector;

import java.util.List;
import java.util.Map;

/** Class for extracting primitive data given dataset
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
    <T extends ICollector> List<DataObject> extractColumnFrom(T collector, DataObject columnName);

    <T extends ICollector> Map<String, Integer> extractReportFom(T collector, DataObject columnName);
}

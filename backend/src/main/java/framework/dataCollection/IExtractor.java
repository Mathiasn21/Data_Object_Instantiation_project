package framework.dataCollection;

/** Class for extracting primitive data
 * @author Mathias Walter Nilsen
 * @author Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IExtractor{
    /**
     * @param collector {@link ICollector}
     * @param columnName String
     * @return double[]
     */
    double[] extractColumnDataFrom(ICollector collector, String columnName);
}

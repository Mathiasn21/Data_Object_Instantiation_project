package framework.dataCollection;

/** Class for extracting primitive data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
@FunctionalInterface
public interface IExtractor{
    /**
     * @param collector {@link ICollector}
     * @param columnName String
     * @return double[]
     */
    <T extends ICollector> double[] extractColumnDataFrom(T collector, String columnName);
}

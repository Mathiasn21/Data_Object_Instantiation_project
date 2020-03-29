package framework.extractors;


import java.util.List;
import java.util.Map;

/** interface describing a contract for extracting primitive data, execute a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0
 */
public interface IExtractor{
    /**
     * @param columnName String
     * @return double[]
     */
    List<Object> extractColumnFrom (String columnName) throws NoSuchFieldException, IllegalAccessException;
    List<Object> extractAllColumns();
    <T>List<T> extractAllColumns(Class<?> clazz);
    Map<String, Double> extractReportFom(String columnName);

    <T> List<T> extractAllColumnsAsT(Class<T> tClass);
}

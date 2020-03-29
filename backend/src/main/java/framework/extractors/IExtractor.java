package framework.extractors;


import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
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
    @NotNull List<Object> extractColumnFrom (String columnName) throws NoSuchFieldException, IllegalAccessException;
    @NotNull List<Object[]> extractColumns();
    @NotNull List<Object[]> extractColumns(@NotNull Class<?> clazz);
    @NotNull List<Object[]> extractColumns(@NotNull Field ...fields);
    @NotNull List<Object[]> extractColumns(@NotNull String ...columns);
    @NotNull List<Object[]> extractColumns(@NotNull int ...indexes);
    @NotNull <T> List<T> extractAllColumnsAsT(Class<T> tClass);

    @NotNull Map<String, Double> extractReportFom(String columnName);
}

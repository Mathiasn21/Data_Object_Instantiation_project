package framework.extractors;


import framework.exceptions.NoSuchColumnException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/** interface describing a contract for extracting primitive data, execute a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public interface IExtractor{
    void setReportOptions(@NotNull List<ReportOptions> reportOptions);

    @Contract(pure = true)
    @NotNull List<ReportOptions> getReportOptions();


    @Contract(pure = true)
    @NotNull List<Object> extractColumnFrom (@NotNull Field field) throws IllegalAccessException, NoSuchFieldException;

    @Contract(pure = true)
    @NotNull List<Object> extractColumnFrom(@NotNull Method method) throws IllegalAccessException, NoSuchMethodException, NoSuchColumnException;

    @Contract(pure = true)
    @NotNull List<Object> extractColumnFrom (@NotNull String column) throws IllegalAccessException, NoSuchColumnException;


    @Contract(pure = true)
    @NotNull Map<Field, List<Object>> extractColumnsUsingFields(@NotNull List<Field> fields) throws IllegalAccessException, NoSuchFieldException;

    @Contract(pure = true)
    @NotNull Map<Method, List<Object>> extractColumnsUsingMethods(@NotNull List<Method> methods) throws IllegalAccessException, NoSuchColumnException;

    @Contract(pure = true)
    @NotNull Map<String, List<Object>> extractColumnsUsingStrings(@NotNull List<String> columns) throws IllegalAccessException, NoSuchColumnException;

    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReport() throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReportFromFields(@NotNull List<Field> fields) throws IllegalAccessException, NoSuchFieldException;

    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReportUsingMethods(@NotNull List<Method> methods) throws IllegalAccessException, NoSuchFieldException, NoSuchColumnException;

    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReportFromStrings(@NotNull List<String> columns) throws IllegalAccessException;
}

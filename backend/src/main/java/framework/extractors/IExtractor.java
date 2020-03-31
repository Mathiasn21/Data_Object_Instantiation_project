package framework.extractors;


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
    @NotNull List<Object> extractColumnFrom (@NotNull Field field) throws IllegalAccessException;
    @NotNull List<Object> extractColumnFrom (@NotNull String column) throws IllegalAccessException;

    @NotNull List<Object[]> extractColumns(@NotNull Field ...fields) throws IllegalAccessException;
    @NotNull List<Object[]> extractColumns(@NotNull Method ...methods) throws IllegalAccessException;
    @NotNull List<Object[]> extractColumns(@NotNull String ...columns) throws IllegalAccessException;

    @NotNull Map<String, Map<String, Double>> extractReport() throws IllegalAccessException;
    @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Field ...fields) throws IllegalAccessException;
    @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull String ...columns) throws IllegalAccessException;
    @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Method ...methods) throws IllegalAccessException;
}

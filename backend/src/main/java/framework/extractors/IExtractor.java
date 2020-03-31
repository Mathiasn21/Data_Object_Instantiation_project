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
    @NotNull List<Object> extractColumnFrom (@NotNull Field field) throws NoSuchFieldException, IllegalAccessException;
    @NotNull List<Object> extractColumnFrom (@NotNull String column) throws NoSuchFieldException, IllegalAccessException;

    @NotNull List<Object[]> extractColumns(@NotNull Class<?> clazz) throws NoSuchFieldException, IllegalAccessException;
    @NotNull List<Object[]> extractColumns(@NotNull Field ...fields) throws NoSuchFieldException, IllegalAccessException;
    @NotNull List<Object[]> extractColumns(@NotNull Method ...methods) throws NoSuchFieldException, IllegalAccessException;
    @NotNull List<Object[]> extractColumns(@NotNull String ...columns) throws NoSuchFieldException, IllegalAccessException;

    @NotNull Map<String, Double> extractReportFom() throws NoSuchFieldException, IllegalAccessException;
    @NotNull Map<String, Double> extractReportFom(@NotNull Class<?> clazz) throws NoSuchFieldException, IllegalAccessException;
    @NotNull Map<String, Double> extractReportFom(@NotNull Field ...fields) throws NoSuchFieldException, IllegalAccessException;
    @NotNull Map<String, Double> extractReportFom(@NotNull String ...columns) throws NoSuchFieldException, IllegalAccessException;
    @NotNull Map<String, Double> extractReportFom(@NotNull Method ...methods) throws NoSuchFieldException, IllegalAccessException;
}

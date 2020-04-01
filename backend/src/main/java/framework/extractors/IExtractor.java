package framework.extractors;


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
    @Contract(pure = true)
    @NotNull List<Object> extractColumnFrom (@NotNull Field field) throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull List<Object> extractColumnFrom(@NotNull Method method) throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull List<Object> extractColumnFrom (@NotNull String column) throws IllegalAccessException;


    @Contract(pure = true)
    @NotNull Map<Field, Object> extractColumns(@NotNull Field ...fields) throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull Map<Method, Object> extractColumns(@NotNull Method ...methods) throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull Map<String, Object> extractColumns(@NotNull String ...columns) throws IllegalAccessException;


    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReport() throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Field ...fields) throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull String ...columns) throws IllegalAccessException;

    @Contract(pure = true)
    @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Method ...methods) throws IllegalAccessException;
}

package doiframework.core.extractors;

import doiframework.core.annotations.DataObject;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
interface IDataExtractorPool {
    @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromFields(@NotNull Map<Class<? extends DataObject>, List<Field>> classListMap) throws IllegalAccessException;
    @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromNames(@NotNull Map<Class<? extends DataObject>, List<String>> classListMap) throws IllegalAccessException;
    @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromMethods(@NotNull Map<Class<? extends DataObject>, List<Method>> classListMap) throws IllegalAccessException;

    @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromFields(@NotNull Map<Class<? extends DataObject>, List<Field>> classListMap) throws IllegalAccessException;
    @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromStrings(@NotNull Map<Class<? extends DataObject>, List<String>> classListMap) throws IllegalAccessException;
    @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromMethods(@NotNull Map<Class<? extends DataObject>, List<Method>> classListMap) throws IllegalAccessException;

    Map<Class<? extends DataObject>, IDataExtractor> getAllExtractors();

    void setNumberOfThreads(byte number);
    void setThreadPoolExecutor(@NotNull ThreadPoolExecutor pool);
}

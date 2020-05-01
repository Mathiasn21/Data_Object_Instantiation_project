package doiframework.core.extractors;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public interface IDataExtractorPool {
    Map<Class<?>, Map<Field,  List<Object>>> extractAllColumnsFromFields();
    Map<Class<?>, Map<Method, List<Object>>> extractAllColumnsFromMethods();

    Map<Class<?>, Map<Field,  List<Object>>> extractAllColumnsFromFields(@NotNull Map<Class<?>, List<Field>> classListMap);
    Map<Class<?>, Map<String, List<Object>>> extractAllColumnsFromNames(@NotNull Map<Class<?>, List<String>> classListMap);
    Map<Class<?>, Map<Method, List<Object>>> extractAllColumnsFromMethods(@NotNull Map<Class<?>, List<Method>> classListMap);

    Map<? extends Class<?>, Map<String, Map<String, Double>>> extractDataReportsFromFields(@NotNull Map<Class<?>, List<Field>> classListMap);
    Map<? extends Class<?>, Map<String, Map<String, Double>>> extractAllReportsFromMethods(@NotNull Map<Class<?>, List<Method>> classListMap);

    @NotNull Map<Class<?>, List<IDataExtractor>> getAllExtractors();

    void setNumberOfThreads(byte number);
    void setThreadPoolExecutor(@NotNull ThreadPoolExecutor pool);
}

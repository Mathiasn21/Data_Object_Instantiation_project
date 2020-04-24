package doiframework.core.extractors;

import doiframework.core.annotations.DataObject;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.collectors.IDataCollectorPool;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class DataExtractorPool implements IDataExtractorPool {
    private byte threads = 2;
    private ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
    private final Map<Class<? extends DataObject>, IDataExtractor> dataExtractors = new HashMap<>();

    public DataExtractorPool(@NotNull List<IDataExtractor> dataExtractors) {
        for (IDataExtractor dataExtractor : dataExtractors) {
            this.dataExtractors.put(dataExtractor.getDataObjectClass(), dataExtractor);
        }
    }

    public DataExtractorPool(@NotNull IDataCollectorPool dataCollectorPool) {
        for (IDataCollector dataCollector : dataCollectorPool.getAllCollectors()) {
            var dataExtractor = new DataExtractor<>(dataCollector);
            dataExtractors.put(dataExtractor.getDataObjectClass(), dataExtractor);
        }
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromFields(@NotNull Map<Class<? extends DataObject>, List<Field>> classListMap) throws IllegalAccessException {
        Map<Class<? extends DataObject>, List<Object[]>> res = new HashMap<>();
        System.out.println(classListMap.keySet());

        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromNames(@NotNull Map<Class<? extends DataObject>, List<String>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromMethods(@NotNull Map<Class<? extends DataObject>, List<Method>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromFields(@NotNull Map<Class<? extends DataObject>, List<Field>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromStrings(@NotNull Map<Class<? extends DataObject>, List<String>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromMethods(@NotNull Map<Class<? extends DataObject>, List<Method>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Contract(pure = true)
    @Override
    public @NotNull Map<Class<? extends DataObject>, IDataExtractor> getAllExtractors() {
        return Collections.unmodifiableMap(dataExtractors);
    }

    @Override
    public void setNumberOfThreads(byte number) { threads = number; }

    @Override
    public void setThreadPoolExecutor(@NotNull ThreadPoolExecutor pool) {
        this.pool = pool;
    }
}

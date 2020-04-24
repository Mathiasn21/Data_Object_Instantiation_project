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
    public @NotNull Map<Class<?>, Map<Field, List<Object>>> extractAllColumnsFromFields(@NotNull Map<Class<?>, List<Field>> classListMap) throws IllegalAccessException {
        Map<Class<?>, Map<Field, List<Object>>> res = new HashMap<>();
        classListMap.keySet().forEach((o) -> {
            var extractor = dataExtractors.get(o);
            try {
                res.put(o, extractor.extractColumnsUsingFields(classListMap.get(o)));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });

        return res;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromNames(@NotNull Map<Class<?>, List<String>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromMethods(@NotNull Map<Class<?>, List<Method>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromFields(@NotNull Map<Class<?>, List<Field>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromStrings(@NotNull Map<Class<?>, List<String>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Override
    public @NotNull Map<Class<? extends DataObject>, Map<String, Map<String, Double>>> extractAllReportsFromMethods(@NotNull Map<Class<?>, List<Method>> classListMap) throws IllegalAccessException {
        return null;
    }

    @Contract(pure = true)
    @Override
    public @NotNull Map<Class<?>, IDataExtractor> getAllExtractors() {
        return Collections.unmodifiableMap(dataExtractors);
    }

    @Override
    public void setNumberOfThreads(byte number) { threads = number; }

    @Override
    public void setThreadPoolExecutor(@NotNull ThreadPoolExecutor pool) {
        this.pool = pool;
    }
}

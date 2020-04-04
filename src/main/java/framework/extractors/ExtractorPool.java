package framework.extractors;

import framework.annotations.DataObject;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class ExtractorPool implements IExtractPool {
    private byte threads = 2;
    private ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    @Override
    public @NotNull Map<Class<? extends DataObject>, List<Object[]>> extractAllColumnsFromFields(@NotNull Map<Class<? extends DataObject>, List<Field>> classListMap) throws IllegalAccessException {
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

    @Override
    public @NotNull List<IExtractor> getAllExtractors() {
        return null;
    }

    @Override
    public void setNumberOfThreads(byte number) { threads = number; }

    @Override
    public void setThreadPoolExecutor(@NotNull ThreadPoolExecutor pool) {
        this.pool = pool;
    }
}

package framework.extractors;

import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21 - Architecture and most of the technical implementation
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 2.2.0
 */
public final class Extractor<C extends ICollector> implements IExtractor {
    private final List<Object> columns;//List of data objects
    private ICollector collector;//Leave this be!
    private List<Exception> exceptions = new ArrayList<>();

    public Extractor(@NotNull C collector) {
        this.columns = collector.getAllColumns();
        this.collector = collector;
    }

    public Extractor(@NotNull List<Object> rows) {
        this.columns = rows;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Field field) throws IllegalAccessException {
        List<Object> res = new ArrayList<>();
        for (Object object : columns) {
            res.add(field.get(object));
        }
        return res;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Method method) throws IllegalAccessException {
        List<Object> res = new ArrayList<>();
        try {
            for (Object object : columns) {
                res.add(method.invoke(object));
            }//Assumes there aint any params
        } catch (InvocationTargetException e) {
            exceptions.add(e);
        }
        return res;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull String column) throws IllegalAccessException {
        List<Object> res = new ArrayList<>();
        Object sample = columns.get(0);//Get a sample object
        Class<?> clazz = sample.getClass();

        //First try to gather either a field and or a method to utilize
        Field field = getField(clazz, column);
        Method method = getMethod(clazz, column);

        if (field != null) {
            for (Object object : columns) {
                res.add(field.get(object));
            }
        } else if (method != null) {
            for (Object object : columns) {
                try {
                    res.add(method.invoke(object));
                } catch (InvocationTargetException e) {
                    exceptions.add(e);
                }
            }
        }
        return res;
    }


    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<Field, List<Object>> extractColumns(@NotNull Field... fields) throws IllegalAccessException {
        Map<Field, List<Object>> res = new HashMap<>();
        for (Field field : fields) {
            res.put(field, this.extractColumnFrom(field));
        }
        return res;
    }

    @Contract(pure = true)
    @Override
    public @NotNull Map<Method, List<Object>> extractColumns(@NotNull Method... methods) throws IllegalAccessException {
        Map<Method, List<Object>> res = new HashMap<>();
        for (Method method : methods) {
            res.put(method, this.extractColumnFrom(method));
        }
        return res;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, List<Object>> extractColumns(@NotNull String... columns) throws IllegalAccessException {
        Map<String, List<Object>> res = new HashMap<>();
        for (String column : columns) {
            res.put(column, this.extractColumnFrom(column));
        }
        return res;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReport() throws IllegalAccessException {
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Field... fields) throws IllegalAccessException {
        for (Field fld : fields) {

        }
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull String... columns) throws IllegalAccessException {
        for (String str : columns) {

        }
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Method... methods) throws IllegalAccessException {
        for (Method mthd : methods) {

        }
        return null;
    }

    @NotNull
    @Contract(pure = true)
    public List<Exception> getErrors() {
        return Collections.unmodifiableList(exceptions);
    }


    @Nullable
    private Method getMethod(@NotNull Class<?> clazz, @NotNull String column) {
        Method method = null;
        try {
            method = clazz.getMethod("get" + column);
        } catch (NoSuchMethodException e) {
        }
        return method;
    }

    @Nullable
    private Field getField(@NotNull Class<?> clazz, String name) {
        Field field = null;
        try {
            field = clazz.getField(name);
        } catch (NoSuchFieldException | SecurityException e) {
            exceptions.add(e);
        }
        return field;
    }
}

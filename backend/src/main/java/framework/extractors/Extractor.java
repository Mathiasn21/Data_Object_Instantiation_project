package framework.extractors;

import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 2.0.1
 */
public final class Extractor<C extends ICollector> implements IExtractor {
    private final List<Object> columns;//List of data objects
    private final ICollector collector;//Leave this be!
    private List<Throwable> throwables = new ArrayList<>();

    public Extractor(@NotNull C collector) {
        this.columns = collector.getAllColumns();
        this.collector = collector;
    }


    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Field field) throws IllegalAccessException {
        //FIXME: Here you are supposed to utilize the field you get and just get data using that......
        List<Object> res = new ArrayList<>();
        Object o = columns.get(0);
        Class<?> clazz = o.getClass();

        Method method = null;
        Field fieldFound = null;
        try{
            fieldFound = clazz.getField("column");
            method = clazz.getMethod("get" + fieldFound);
        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException e) {
            throwables.add(e);
        }finally{
            if(fieldFound != null){
                for (Object object : columns) { res.add(fieldFound.get(object)); }
            }else{
                if(method != null){
                    for (Object object : columns) {
                        try { res.add(method.invoke(object));
                        } catch (InvocationTargetException e) {
                            throwables.add(e);
                        }
                    }
                }
            }
        }
        return res;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull String column) throws IllegalAccessException {
        List<Object> res = new ArrayList<>();
        Object o = columns.get(0);
        Class<?> clazz = o.getClass();

        Method method = null;
        Field field = null;
        try{
            field = clazz.getField("column");
            method = clazz.getMethod("get" + column);
        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException e) {
            throwables.add(e);
        }finally{
            if(field != null){
                for (Object object : columns) { res.add(field.get(object)); }
            }else{
                if(method != null){
                    for (Object object : columns) {
                        try { res.add(method.invoke(object));
                        } catch (InvocationTargetException e) {
                            throwables.add(e);
                        }
                    }
                }
            }
        }
        return res;
    }

    //TODO: implement this method
    @NotNull
    @Contract(pure = true)
    public List<Object[]> extractColumns(@NotNull Class<?> clazz) {
        return new ArrayList<>();
    }

    //TODO: implement this method
    @NotNull
    @Contract(pure = true)
    @Override
    public List<Object[]> extractColumns(@NotNull Field... fields) { return null; }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object[]> extractColumns(@NotNull Method... methods) throws IllegalAccessException {
        return null;
    }

    //TODO: implement this method
    @NotNull
    @Contract(pure = true)
    @Override
    public List<Object[]> extractColumns(@NotNull String... columns) {
        return null;
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
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull String... columns) throws IllegalAccessException {
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Method... methods) throws IllegalAccessException {
        return null;
    }

    @NotNull
    @Contract(pure = true)
    public List<Throwable> getErrors() {
        return Collections.unmodifiableList(throwables);
    }
}

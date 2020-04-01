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
    private List<Exception> exceptions = new ArrayList<>();

    public Extractor(@NotNull C collector) {
        this.columns = collector.getAllColumns();
        this.collector = collector;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Field field) throws IllegalAccessException {
        List<Object> res = new ArrayList<>();
        Class<?> fieldClass = field.getClass();

        Method fieldFound = null;
        try{
            fieldFound = fieldClass.getMethod("get" + field);
        } catch (SecurityException | NoSuchMethodException e) {
            exceptions.add(e);
        }finally{
            if(fieldFound != null){
                for (Object object : columns) { res.add(field.get(object)); }
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

        try{ field = clazz.getField("column");
        } catch (NoSuchFieldException | SecurityException e) { exceptions.add(e); }

        try { method = clazz.getMethod("get" + column);
        } catch (NoSuchMethodException e) { }

        if(field != null){
            for (Object object : columns) { res.add(field.get(object)); }
        }else if(method != null){
            for (Object object : columns) {
                try { res.add(method.invoke(object));
                } catch (InvocationTargetException e) {
                    exceptions.add(e);
                }
            }
        }
        return res;
    }

    //TODO: implement this method
    @NotNull
    @Contract(pure = true)
    @Override
    public List<Object[]> extractColumns(@NotNull Field... fields) throws IllegalAccessException {
        List<Object[]> res = new ArrayList<>();
        for(Field fld:fields) {
            Class<?> fieldClass = fld.getClass();

            Method fieldFound = null;
            try{
                fieldFound = fieldClass.getMethod("get" + fld);
            } catch (SecurityException | NoSuchMethodException e) {
                exceptions.add(e);
            }finally{
                if(fieldFound != null){
                    for (Object object : columns) { res.add((Object[]) fld.get(object)); }
                }
            }
        }
        return res;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object[]> extractColumns(@NotNull Method... methods) throws IllegalAccessException {
        //FIXME: Here you are supposed to utilize the methods you get and just get data using that......
        for(Method mthd:methods) {

        }
        return null;
    }

    //TODO: implement this method
    @NotNull
    @Contract(pure = true)
    @Override
    public List<Object[]> extractColumns(@NotNull String... columns) throws IllegalAccessException {
        List<Object[]> res = new ArrayList<>();
        Object o = this.columns.get(0);
        Class<?> clazz = o.getClass();

        Method method = null;
        Field field = null;
        for(String str:columns) {
            try{
                field = clazz.getField("column");
                method = clazz.getMethod("get" + str);
            } catch (NoSuchFieldException | SecurityException | NoSuchMethodException e) {
                exceptions.add(e);
            }finally{
                if(field != null){
                    for (Object object : this.columns) { res.add((Object[]) field.get(object)); }
                }else{
                    if(method != null){
                        for (Object object : this.columns) {
                            try { res.add((Object[]) method.invoke(object));
                            } catch (InvocationTargetException | IllegalAccessException e) {
                                exceptions.add(e);
                            }
                        }
                    }
                }
            }
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
        for(Field fld:fields) {

        }
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull String... columns) throws IllegalAccessException {
        for(String str:columns) {

        }
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFrom(@NotNull Method... methods) throws IllegalAccessException {
        for(Method mthd:methods) {

        }
        return null;
    }

    @NotNull
    @Contract(pure = true)
    public List<Exception> getErrors() {
        return Collections.unmodifiableList(exceptions);
    }
}

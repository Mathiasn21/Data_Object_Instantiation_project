package DOIFramework.extractors;

import DOIFramework.collectors.ICollector;
import DOIFramework.exceptions.NoSuchColumnException;
import DOIFramework.observer.EventObserver;
import DOIFramework.observer.events.ExceptionEvent;
import DOIFramework.observer.events.ExtractorFinishedEvent;
import DOIFramework.observer.events.IEvent;
import DOIFramework.statistics.Average;
import DOIFramework.utilities.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21 - Architecture and most of the technical implementation
 * @version 2.2.0
 */
public final class Extractor<C extends ICollector> implements IExtractor {
    private final List<Object> columns;//List of resource objects
    private ICollector collector;
    private List<Exception> exceptions = new ArrayList<>();
    private List<AverageReport> reportOptions = Arrays.asList(AverageReport.values());

    public Extractor(@NotNull C collector) {
        this.columns = collector.getAllColumns();
        this.collector = collector;
    }

    public Extractor(@NotNull List<Object> rows) {
        this.columns = rows;
    }

    @Override
    public void setReportOptions(@NotNull List<AverageReport> reportOptions) {
        this.reportOptions = reportOptions;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<AverageReport> getReportOptions() {
        return Collections.unmodifiableList(reportOptions);
    }


    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Field field) throws NoSuchFieldException {
        List<Object> res = new ArrayList<>();
        for (Object object : columns) {
            try { res.add(field.get(object));
            } catch (IllegalAccessException e) {
                raise(new ExceptionEvent(this, e));
                throw new NoSuchFieldException("Could not access field" + field.getName());
            }
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    /**
     * @param method {@link Method}
     * @return {@link List}&lt;
     * @throws NoSuchColumnException {@link NoSuchColumnException} NoSuchColumnException
     */
    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Method method) throws NoSuchColumnException {
        List<Object> res = new ArrayList<>();
        try {
            for (Object object : columns) {
                res.add(method.invoke(object));
            }//Assumes there aint any params

        } catch (InvocationTargetException | IllegalAccessException e) {
            raise(new ExceptionEvent(this, e));
            throw new NoSuchColumnException("Unable to access that method: " + method.getName());
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull String column) throws NoSuchColumnException {
        List<Object> res = new ArrayList<>();
        Object sample = columns.get(0);//Get a sample object
        Class<?> clazz = sample.getClass();

        //First try to gather either a field and or a method to utilize
        Field field = getField(clazz, column);
        Method method = getMethod(clazz, column);

        try {
            if (field != null) {
                for (Object object : columns) {
                    res.add(field.get(object));
                }
            } else if (method != null) {
                for (Object object : columns) {
                    res.add(method.invoke(object));
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            raise(new ExceptionEvent(this, e));
            throw new NoSuchColumnException("Unable to access column by field or method.");
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }


    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<Field, List<Object>> extractColumnsUsingFields(@NotNull List<Field> fields) throws NoSuchFieldException {
        Map<Field, List<Object>> res = new HashMap<>();
        for (Field field : fields) {
            res.put(field, this.extractColumnFrom(field));
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    @Contract(pure = true)
    @Override
    public @NotNull Map<Method, List<Object>> extractColumnsUsingMethods(@NotNull List<Method> methods) throws NoSuchColumnException {
        Map<Method, List<Object>> res = new HashMap<>();
        for (Method method : methods) {
            res.put(method, this.extractColumnFrom(method));
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, List<Object>> extractColumnsUsingStrings(@NotNull List<String> columns) throws NoSuchColumnException {
        Map<String, List<Object>> res = new HashMap<>();
        for (String column : columns) {
            res.put(column, this.extractColumnFrom(column));
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReport() {
        raise(new ExtractorFinishedEvent(this));
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    @SuppressWarnings("unchecked")//Safe as the list is guaranteed to be filtered beforehand
    public @NotNull Map<String, Map<String, Double>> extractReportUsingFields(@NotNull List<Field> fields) throws NoSuchFieldException {
        Map<String, Map<String, Double>> res = new HashMap<>();
        List<Field> filteredFields = filterFieldsForPrimitiveNumbers(fields);
        Map<Field, List<Object>> columns = extractColumnsUsingFields(filteredFields);

        for (Field field : filteredFields) {
            Map<String, Double> report = new HashMap<>();
            List<Number> column = (List<Number>)(Object)columns.get(field);//Safe as this is ensured beforehand

            for (AverageReport option : reportOptions) {
                Average average = new Average(column);
                report.put(option.option, option.calculate.execute(average));
            }
            res.put(field.getName(), report);
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    @SuppressWarnings("unchecked")//Safe as the list is guaranteed to be filtered beforehand
    public @NotNull Map<String, Map<String, Double>> extractReportUsingMethods(@NotNull List<Method> methods) throws NoSuchColumnException {
        Map<String, Map<String, Double>> res = new HashMap<>();
        List<Method> filteredMethods = filterMethodsForPrimitiveNumbers(methods);
        Map<Method, List<Object>> columns = extractColumnsUsingMethods(filteredMethods);

        for (Method method : filteredMethods) {
            Map<String, Double> report = new HashMap<>();
            List<Number> column = (List<Number>)(Object)columns.get(method);//Safe as this is ensured beforehand

            for (AverageReport option : reportOptions) {
                Average average = new Average(column);
                report.put(option.option, option.calculate.execute(average));
            }
            res.put(method.getName(), report);
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReportFromStrings(@NotNull List<String> columns) {
        for (String string : columns) {

        }
        raise(new ExtractorFinishedEvent(this));
        return null;
    }

    @NotNull
    private List<Field> filterFieldsForPrimitiveNumbers(@NotNull List<Field> fields) {
        List<Field> filteredFields = new ArrayList<>();
        fields.forEach((field) -> {
            if(Parser.isPrimitiveNumber(field.getType())){
                filteredFields.add(field);
            }
        });
        return filteredFields;
    }

    @NotNull
    private List<Method> filterMethodsForPrimitiveNumbers(@NotNull List<Method> methods) {
        List<Method> filteredFields = new ArrayList<>();
        methods.forEach((field) -> {
            if(Parser.isPrimitiveNumber(field.getReturnType())){
                filteredFields.add(field);
            }
        });
        return filteredFields;
    }

    @Nullable
    private Method getMethod(@NotNull Class<?> clazz, @NotNull String column) {
        Method method = null;
        try {
            method = clazz.getMethod("get" + column);
        } catch (NoSuchMethodException e) {
            raise(new ExceptionEvent(this, e));
        }
        return method;
    }

    @Nullable
    private Field getField(@NotNull Class<?> clazz, String name) {
        Field field = null;
        try {
            field = clazz.getField(name);
        } catch (NoSuchFieldException | SecurityException e) {
            raise(new ExceptionEvent(this, e));
        }
        return field;
    }

    private void raise(@NotNull IEvent event) { EventObserver.registerEventFrom(event); }
}

package doiframework.core.extractors;

import doiframework.core.collectors.IDataCollector;
import doiframework.exceptions.NoSuchColumnException;
import doiframework.core.observer.EventObserver;
import doiframework.core.observer.events.ExceptionEvent;
import doiframework.core.observer.events.ExtractorFinishedEvent;
import doiframework.core.observer.events.IEvent;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.exceptions.UnableToAccessDataException;
import doiframework.statistics.report.ReportCommand;
import doiframework.statistics.report.Report;
import doiframework.utilities.Parser;
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
public final class DataExtractor<C extends IDataCollector> implements IDataExtractor {
    private final List<Object> columns;//List of resource objects
    private IDataCollector collector;
    private Report[] reportOptions = Report.getFullAverageReport();

    public DataExtractor(@NotNull C collector) {
        this.columns = collector.getAllColumns();
        this.collector = collector;
    }

    public DataExtractor(@NotNull List<Object> rows) {
        this.columns = rows;
    }

    @Override
    public void setReportOptions(@NotNull List<Report> reportOptions) {
        this.reportOptions = reportOptions.toArray(new Report[0]);
    }

    @Override
    public void setReportOptions(@NotNull Report[] reportOptions) {
        this.reportOptions = reportOptions;
    }

    @Contract(pure = true)
    @Override
    public @NotNull List<Report> getReportOptions() {
        return Arrays.asList(reportOptions);
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
     * @throws NoSuchColumnException NoSuchColumnException
     */
    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Method method) throws NoSuchColumnException {
        List<Object> res = new ArrayList<>();
        try {
            for (Object object : columns) {
                res.add(method.invoke(object));
            }//Assumes there ain't any params

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

    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Map<String, Double>> extractReport() throws NoSuchFieldException, NoSuchColumnException, UnableToAccessDataException, NotPrimitiveNumberException {
        Map<String, Map<String, Double>> res;

        Object sample = columns.get(0);//Get a sample object
        Class<?> clazz = sample.getClass();

        Field[] fields = clazz.getFields();
        Method[] methods = clazz.getMethods();

        if(fields.length > 0){
            res = extractReportUsingFields(Arrays.asList(fields));
        }else if(methods.length > 0){
            res = extractReportUsingMethods(Arrays.asList(methods));
        }else{
            throw new UnableToAccessDataException("Try making fields public or have getters.");
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    @Contract(pure = true)
    @Override
    @SuppressWarnings("unchecked")//Safe as the list is guaranteed to be filtered beforehand
    public @NotNull Map<String, Map<String, Double>> extractReportUsingFields(@NotNull List<Field> fields) throws NoSuchFieldException, NotPrimitiveNumberException {
        Map<String, Map<String, Double>> res = new HashMap<>();
        List<Field> filteredFields = filterFieldsForPrimitiveNumbers(fields);
        Map<Field, List<Object>> columns = extractColumnsUsingFields(filteredFields);

        for (Field field : filteredFields) {
            List<Number> column = (List<Number>) (Object) columns.get(field);//Safe as this is ensured beforehand
            ReportCommand centralCommand = new ReportCommand(reportOptions, column);
            res.put(field.getName(), centralCommand.executeReport());
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    @Contract(pure = true)
    @Override
    @SuppressWarnings("unchecked")//Safe as the list is guaranteed to be filtered beforehand
    public @NotNull Map<String, Map<String, Double>> extractReportUsingMethods(@NotNull List<Method> methods) throws NoSuchColumnException, NotPrimitiveNumberException {
        Map<String, Map<String, Double>> res = new HashMap<>();
        List<Method> filteredMethods = filterMethodsForPrimitiveNumbers(methods);
        Map<Method, List<Object>> columns = extractColumnsUsingMethods(filteredMethods);

        for (Method method : filteredMethods) {
            List<Number> column = (List<Number>) (Object) columns.get(method);//Safe as this is ensured beforehand
            ReportCommand centralCommand = new ReportCommand(reportOptions, column);
            res.put(method.getName(), centralCommand.executeReport());
        }
        raise(new ExtractorFinishedEvent(this));
        return res;
    }

    /**
     * @return IDataCollector
     */
    public IDataCollector getCollector() {
        return collector;
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

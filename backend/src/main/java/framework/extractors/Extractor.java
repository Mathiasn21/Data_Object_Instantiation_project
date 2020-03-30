package framework.extractors;

import framework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Class used for extracting information from a collector
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0
 */
public final class Extractor<C extends ICollector> implements IExtractor {
    private final C collector;
    private final List<Object> columns;//List of data objects

    public Extractor(@NotNull C collector) {
        this.collector = collector;
        this.columns = collector.getAllColumns();
    }


    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull Field field) throws NoSuchFieldException, IllegalAccessException {
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(@NotNull String column) throws NoSuchFieldException, IllegalAccessException {
        List<Object> data = null;
        //Get all data from collector
        List<Object> allColumns = collector.getAllColumns();

        //Grab thoose fields that match with the following column names.
        //If none, try grabbing methods/getters that match with the column name

        //Either, use the get data by using the fields or by invoking methods


        //Return Object[] where each index is a column

        return new ArrayList<>();
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull List<Object> extractColumnFrom(int index) throws NoSuchFieldException, IllegalAccessException {
        return null;
    }



    //TODO: implement this method
    /**
     * @return returns all columns from dataset
     */
    @Contract(pure = true)
    @NotNull
    @Override
    public final List<Object[]> extractColumns(){
        return new ArrayList<>();
    }

    //TODO: implement this method
    @NotNull
    @Contract(pure = true)
    public List<Object[]> extractColumns(@NotNull Class<?> clazz) {
        List<Object> allColumns = collector.getAllColumns();
        Class<?> collectorClazz = collector.getClazz();
        return new ArrayList<>();
    }

    //TODO: implement this method
    @NotNull
    @Contract(pure = true)
    @Override
    public List<Object[]> extractColumns(@NotNull Field... fields) {
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
    public @NotNull List<Object[]> extractColumns(@NotNull int... indexes) {
        return null;
    }

    @Override
    public @NotNull Map<String, Double> extractReportFom() throws NoSuchFieldException, IllegalAccessException {
        return null;
    }


    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Double> extractReportFom(@NotNull Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Double> extractReportFom(@NotNull Field... fields) throws NoSuchFieldException, IllegalAccessException {
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Double> extractReportFom(@NotNull String... columns) throws NoSuchFieldException, IllegalAccessException {
        return null;
    }

    //TODO: implement this method
    @Contract(pure = true)
    @Override
    public @NotNull Map<String, Double> extractReportFom(@NotNull int... indexes) throws NoSuchFieldException, IllegalAccessException {
        return null;
    }
}

package doiframework.statistics.calculations;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class Statistics {
    protected final double[] data;
    protected final int n;


    @Contract(pure = true)
    public Statistics(@NotNull double[] data) {
        this.data = data;
        this.n = data.length;
    }

    /**
     * @param data Double[]
     */
    @Contract(pure = true)
    public Statistics(@NotNull Double[] data){
        int length = data.length;
        double[] doubles = new double[length];
        for(int i = 0; i < length; i++){ doubles[i] = data[i]; }

        this.data = doubles;
        this.n = length;
    }

    /**
     * @param data Number[]
     */
    @Contract(pure = true)
    public Statistics(@NotNull Number[] data){
        int length = data.length;
        double[] doubles = new double[length];
        for(int i = 0; i < length; i++){ doubles[i] = data[i].doubleValue(); }

        this.data = doubles;
        this.n = length;
    }

    /**
     * @param data {@link List}&lt;{@link Number}&gt;
     */
    @Contract(pure = true)
    public Statistics(@NotNull List<Number> data){
        int size = data.size();
        double[] doubles = new double[size];

        for(int i = 0; i < size; i++){ doubles[i] = data.get(i).doubleValue(); }
        this.data = doubles;
        this.n = size;
    }

}

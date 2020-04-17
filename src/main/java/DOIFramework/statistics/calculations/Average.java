package DOIFramework.statistics.calculations;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class for calculating average methods execute the resource
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class Average implements IStatistics{
    private final double[] data;
    private final int n;
    private double sum = 0;

    /**
     * @param data double[]
     */
    @Contract(pure = true)
    public Average(@NotNull double[] data){
        this.data = data;
        this.n = data.length;
    }

    /**
     * @param data double[]
     */
    @Contract(pure = true)
    public Average(@NotNull Double[] data){
        double[] doubles = new double[data.length];
        for(int i = 0; i < data.length; i++){ doubles[i] = data[i]; }
        this.data = doubles;
        this.n = data.length;
    }

    /**
     * @param data {@link List}&lt;{@link Number}&gt;
     */
    @Contract(pure = true)
    public Average(@NotNull List<Number> data){
        double[] doubles = new double[data.size()];
        for(int i = 0; i < data.size(); i++){ doubles[i] = data.get(i).doubleValue(); }
        this.data = doubles;
        this.n = data.size();
    }

    /**
     * @return double
     */
    public double calcSum() {
        if(sum <= 0){
            for(double i : data){
                sum += i;
            }
        }
        return sum;
    }

    /**
     * @return double
     */
    public double calcMean() {
        return (sum == 0 ? calcSum() : sum) / n;
    }

    /**
     * @return double
     */
    public double calcMedian() {
        double calcMid = n >> 1;
        int lowerBound = (int)(calcMid -1);
        int upperBound = (int)calcMid;
        if (n % 2 == 0){
            return (data[lowerBound] + data[upperBound]) / 2;}
        return data[upperBound];
    }

    /**
     * @return double
     */
    public double calcMode() {
        double maxValue = 0, maxCount = 0;
        int i, j;

        for (i = 0; i < n; ++i) {
            int count = 0;
            for (j = 0; j < n; ++j) {
                if (data[j] == data[i])
                    ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = data[i];
            }
        }
        return maxValue;
    }

    /**
     * @return double
     */
    public double calcMidRange() {
        double minValue = data[0];
        double maxValue = data[n - 1];
        return (minValue + maxValue) / 2;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "Average calculation";
    }
}

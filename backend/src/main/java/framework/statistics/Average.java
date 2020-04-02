package framework.statistics;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class for calculating average methods execute the data {@link IAverage}
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class Average implements IAverage{

    private final double[] data;
    private final int length;
    private double sum = 0;

    /**
     * @param data double[]
     */
    @Contract(pure = true)
    public Average(@NotNull double[] data){
        this.data = data;
        this.length = data.length;
    }

    /**
     * @param data double[]
     */
    @Contract(pure = true)
    public Average(@NotNull Double[] data){
        double[] doubles = new double[data.length];
        for(int i = 0; i < data.length; i++){ doubles[i] = data[i]; }
        this.data = doubles;
        this.length = data.length;
    }

    /**
     * @return double
     */
    @Override
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
        return (sum == 0 ? calcSum() : sum) / length;
    }

    /**
     * @return double
     */
    @Override
    public double calcMedian() {
        double calcMid = length >> 1;
        int lowerBound = (int)(calcMid -1);
        int upperBound = (int)calcMid;
        if (length % 2 == 0){
            return (data[lowerBound] + data[upperBound]) / 2;}
        return data[upperBound];
    }

    /**
     * @return double
     */
    @Override
    public double calcMode() {
        double maxValue = 0, maxCount = 0;
        int i, j;

        for (i = 0; i < length; ++i) {
            int count = 0;
            for (j = 0; j < length; ++j) {
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
    @Override
    public double calcMidRange() {
        double minValue = data[0];
        double maxValue = data[length - 1];
        return (minValue + maxValue) / 2;
    }

    @Override
    public String toString() {
        return "Average calculation";
    }
}

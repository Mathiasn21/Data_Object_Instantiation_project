package doiframework.statistics.calculations;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class for calculating statistics execute the resource {@link Statistics}
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class SimpleStatistics extends Statistics {
    private final Average average;

    /**
     * @param data double[]
     **/
    @Contract(pure = true)
    public SimpleStatistics(@NotNull double[] data){
        super(data);
        this.average = new Average(data);
    }

    /**
     * @param data double[]
     */
    @Contract(pure = true)
    public SimpleStatistics(@NotNull Double[] data){
        super(data);
        this.average = new Average(data);
    }

    /**
     * @param data Number[]
     */
    @Contract(pure = true)
    public SimpleStatistics(@NotNull Number[] data){
        super(data);
        this.average = new Average(data);
    }
    /**
     * @param data {@link List}&lt;{@link Number}&gt;
     */
    @Contract(pure = true)
    public SimpleStatistics(@NotNull List<Number> data){
        super(data);
        this.average = new Average(data);
    }


    /**
     * @return double
     */
    public double calcSampleVariance() {
        double sum = 0;
        double avg = average.calcMean();
        for (double value : data) {
            double diff = value - avg;
            diff *= diff;
            sum += diff;
        }
        return sum  / (n -1);
    }

    /**
     * @return double
     */
    public double calcPopulationVariance() {
        double sumDiffsSquared = 0.0;
        double avg = average.calcMean();
        for (double value : data) {
            double diff = value - avg;
            diff *= diff;
            sumDiffsSquared += diff;
        }
        return sumDiffsSquared  / n;
    }

    /**
     * @return double
     */
    public double calcStandardDeviationFromSample() {
        double variance = calcSampleVariance();
        return Math.sqrt(variance);
    }

    /**
     * @return double
     */
    public double calcStandardDeviationFromPopulation() {
        double variance = calcPopulationVariance();
        return Math.sqrt(variance);
    }

    public double calcStandardErrorFromSample() {
        return calcStandardDeviationFromSample() / Math.sqrt(n);
    }

    public double calcStandardErrorFromPopulation() {
        return calcStandardDeviationFromSample() / Math.sqrt(n);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return "SimpleStatistics calculation";
    }
}

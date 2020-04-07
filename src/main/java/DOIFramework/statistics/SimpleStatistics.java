package DOIFramework.statistics;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class for calculating statistics execute the resource {@link IStatistics}
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class SimpleStatistics implements IStatistics {

    private final double[] data;
    private final int length;
    private final Average average;

    /**
     * @param data double[]
     **/
    @Contract(pure = true)
    public SimpleStatistics(@NotNull double[] data){
        this.data = data;
        this.length = data.length;
        this.average = new Average(data);
    }

    /**
     * @return double
     */
    @Override
    public double calcSampleVariance() {
        double sum = 0;
        double avg = average.calcMean();
        for (double value : data) {
            double diff = value - avg;
            diff *= diff;
            sum += diff;
        }
        return sum  / (length -1);
    }

    /**
     * @return double
     */
    @Override
    public double calcPopulationVariance() {
        double sumDiffsSquared = 0.0;
        double avg = average.calcMean();
        for (double value : data) {
            double diff = value - avg;
            diff *= diff;
            sumDiffsSquared += diff;
        }
        return sumDiffsSquared  / length;
    }

    /**
     * @return double
     */
    @Override
    public double calcStandardDeviationFromSample() {
        double variance = calcSampleVariance();
        return Math.sqrt(variance);
    }

    /**
     * @return double
     */
    @Override
    public double calcStandardDeviationFromPopulation() {
        double variance = calcPopulationVariance();
        return Math.sqrt(variance);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return "SimpleStatistics calculation";
    }
}

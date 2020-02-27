package framework.statistics;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class for calculating statistics from the data {@link IStatistics}
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class StandardStatistics implements IStatistics {

    private final double[] data;
    private final int length;
    private final Average average;

    /**
     * @param data double[]
     */
    @Contract(pure = true)
    public StandardStatistics(@NotNull double[] data){
        this.data = data;
        this.length = data.length;
        this.average = new Average(data);
    }


    /**
     * @return double
     */
    @Override
    public double sampleVariance() {
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
    public double populationVariance() {
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
    public double standardDeviationFromSample() {
        double variance = sampleVariance();
        return Math.sqrt(variance);
    }

    /**
     * @return double
     */
    @Override
    public double standardDeviationFromPopulation() {
        double variance = populationVariance();
        return Math.sqrt(variance);
    }

    @Override
    public double covariance() {
        //TODO: implement method
        return 0;
    }

    @Override
    public double correlation() {
        //TODO: implement method
        return 0;
    }

    @Override
    public String toString() {
        return "StandardStatistics calculation";
    }
}

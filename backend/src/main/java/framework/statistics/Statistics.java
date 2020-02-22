package framework.statistics;

/**
 * Class for calculating statistics from the data {@link IStatistics}
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class Statistics implements IStatistics {

    private final double[] data;
    private final int length;
    private double sum = 0;

    /**
     * @param data double[]
     */
    public Statistics(double[] data){
        this.data = data;
        this.length = data.length;
    }

    /**
     * @return double
     */
    @Override
    public double calcSum() {
        double sum = 0;
        for(double i : data){
            sum += i;
        }
        return sum;
    }

    /**
     * @return double
     */
    public double averageMean() {
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
    public double averageMode() {
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
    public double averageMidRange() {
        double minValue = data[0];
        double maxValue = data[length - 1];
        return (minValue + maxValue) / 2;
    }

    /**
     * @return double
     */
    @Override
    public double sampleVariance() {
        double sum = 0;
        double average = averageMean();
        for (double value : data) {
            double diff = value - average;
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
        double average = averageMean();
        for (double value : data) {
            double diff = value - average;
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
    public String toString() {
        return "Statistics calculation";
    }
}

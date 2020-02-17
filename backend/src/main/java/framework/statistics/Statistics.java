package framework.statistics;

public class Statistics implements IStatistics {

    private final double[] data;
    private final int length;

    public Statistics(double[] data){
        this.data = data;
        this.length = data.length;
    }

    public double averageMean() {
        double[] values = data;
        double total = 0;

        for(double i : values){
            total += i;
        }
        return total / values.length;
    }

    @Override
    public double calcMedian() {
        double calcMid = length >> 1;
        int lowerBound = (int)(calcMid -1);
        int upperBound = (int)calcMid;
        if (length % 2 == 0){
           return (data[lowerBound] + data[upperBound]) / 2;}
        return data[upperBound];
    }

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

    @Override
    public double averageMidRange() {
        double minValue = data[0];
        double maxValue = data[length - 1];
        return (minValue + maxValue) / 2;
    }

    @Override
    public double sampleVariance() {
        double sum = 0;
        double average = averageMean();
        for (double value : data)
        {
            double diff = value - average;
            diff *= diff;
            sum += diff;
        }
        return sum  / (length -1);
    }

    @Override
    public double populationVariance() {
        double sumDiffsSquared = 0.0;
        double average = averageMean();
        for (double value : data)
        {
            double diff = value - average;
            diff *= diff;
            sumDiffsSquared += diff;
        }
        return sumDiffsSquared  / length;
    }

    @Override
    public double standardDeviationFromSample() {
        double variance = sampleVariance();
        return Math.sqrt(variance);
    }

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

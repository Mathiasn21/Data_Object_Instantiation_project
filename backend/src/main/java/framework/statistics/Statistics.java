package framework.statistics;



public class Statistics implements IStatistics {

    private final double[] data;
    private final int length;

    public Statistics(double[] data){
        this.data = data;
        this.length = data.length;
    }

    public double calcAverageMean() {
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
    public double calcAverageMode() {
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
    public double calcAverageMidRange() {
        double minValue = data[0];
        double maxValue = data[length - 1];
        return (minValue + maxValue) / 2;
    }

    @Override
    public double calcSampleVariance() {
        return 0;
    }

    @Override
    public String toString() {
        return "Statistics calculation";
    }
}

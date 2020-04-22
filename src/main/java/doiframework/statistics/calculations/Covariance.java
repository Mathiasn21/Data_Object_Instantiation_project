package doiframework.statistics.calculations;

import doiframework.exceptions.DatasetNotMatchingException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Covariance extends AdvancedStatistics{

    /**
     * @param data1 double[]
     * @param data2 double[]
     **/
    @Contract(pure = true)
    public Covariance(@NotNull double[] data1, @NotNull double[] data2){
        super(data1, data2);
    }

    @Contract(pure = true)
    public Covariance(@NotNull Double[] data1, @NotNull Double[] data2){
        super(data1, data2);
    }
    @Contract(pure = true)
    public Covariance(@NotNull Number[] data1, @NotNull Number[] data2){
        super(data1, data2);
    }
    @Contract(pure = true)
    public Covariance(@NotNull List<Number> data1, @NotNull List<Number> data2){
        super(data1, data2);
    }

    private double covariance() throws DatasetNotMatchingException {
        double sum = 0;
        Average avg = new Average(data);
        Average avg2 = new Average(data2);
        double avgMean = avg.calcMean();
        double avg2Mean = avg2.calcMean();

        if (data.length != data2.length) {
            throw new DatasetNotMatchingException();
        }
        for (int i = 0; i < n; i++) {
            sum += ((data[i] - avgMean) * (data2[i] - avg2Mean));
        }
        return sum;
    }

    public double calcCovarianceFromPopulation() throws DatasetNotMatchingException {
        return covariance() / n;
    }
    public double calcCovarianceFromSample()throws DatasetNotMatchingException  {
        return covariance() / (n-1);
    }

    @Override
    public String toString() {
        return "Covariance Calculation";
    }
}

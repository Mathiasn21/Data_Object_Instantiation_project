package doiframework.statistics.calculations;


import doiframework.exceptions.DatasetNotMatchingException;
import doiframework.statistics.calculations.AdvancedStatistics;
import doiframework.statistics.calculations.Covariance;
import doiframework.statistics.calculations.SimpleStatistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Correlation extends AdvancedStatistics {

    /**
     * @param data1 double[]
     * @param data2 double[]
     **/
    @Contract(pure = true)
    public Correlation(@NotNull double[] data1, @NotNull double[] data2){
        super(data1, data2);
    }
    @Contract(pure = true)
    public Correlation(@NotNull Double[] data1, @NotNull Double[] data2){
        super(data1, data2);
    }
    @Contract(pure = true)
    public Correlation(@NotNull Number[] data1, @NotNull Number[] data2){
        super(data1, data2);
    }
    @Contract(pure = true)
    public Correlation(@NotNull List<Number> data1, @NotNull List<Number> data2){
        super(data1, data2);
    }

    public double calcCorrelationCoefficientFromSample() throws DatasetNotMatchingException {
        Covariance cov = new Covariance(data, data2);
        SimpleStatistics s1 = new SimpleStatistics(data);
        SimpleStatistics s2 = new SimpleStatistics(data2);

        if (data.length != data2.length) {
            throw new DatasetNotMatchingException();
        }
        return cov.calcCovarianceFromSample() /
                (s1.calcStandardDeviationFromSample() * s2.calcStandardDeviationFromSample());
    }

    public double calcCorrelationCoefficientFromPopulation() throws DatasetNotMatchingException {
        Covariance cov = new Covariance(data, data2);
        SimpleStatistics s1 = new SimpleStatistics(data);
        SimpleStatistics s2 = new SimpleStatistics(data2);

        if (data.length != data2.length) {
            throw new DatasetNotMatchingException();
        }
        return cov.calcCovarianceFromPopulation()/
                (s1.calcStandardDeviationFromPopulation() * s2.calcStandardDeviationFromPopulation());
    }

    @Override
    public String toString() {
        return "Correlation Calculation";
    }
}

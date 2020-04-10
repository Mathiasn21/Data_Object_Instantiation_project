package DOIFramework.statistics;


import DOIFramework.exceptions.DatasetsNotMatichingException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Correlation{

    private final double[] data1;
    private final double[] data2;

    /**
     * @param data1 double[]
     * @param data2 double[]
     **/
    @Contract(pure = true)
    public Correlation(@NotNull double[] data1, @NotNull double[] data2){
        this.data1 = data1;
        this.data2 = data2;
    }

    public double calcCorrelationCoefficientFromSample() throws Exception {
        Covariance cov = new Covariance(data1, data2);
        SimpleStatistics s1 = new SimpleStatistics(data1);
        SimpleStatistics s2 = new SimpleStatistics(data2);

        if (data1.length != data2.length) {
            throw new DatasetsNotMatichingException();
        }
        return cov.calcCovarianceFromSample() /
                (s1.calcStandardDeviationFromSample() * s2.calcStandardDeviationFromSample());

        //return cov.calcCovarianceFromSample() / s1.calcStandardError / s2.calcStandardError;
    }

    public double calcCorrelationCoefficientFromPopulation() throws Exception {
        Covariance cov = new Covariance(data1, data2);
        SimpleStatistics s1 = new SimpleStatistics(data1);
        SimpleStatistics s2 = new SimpleStatistics(data2);

        if (data1.length != data2.length) {
            throw new DatasetsNotMatichingException();
        }
        return cov.calcCovarianceFromPopulation()/
                (s1.calcStandardDeviationFromPopulation() * s2.calcStandardDeviationFromPopulation());

    }

}

package DOIFramework.statistics.calculations;

import DOIFramework.exceptions.DatasetNotMatchingException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Covariance {
    private final double[] data1;
    private final double[] data2;
    private final double n;

    /**
     * @param data1 double[]
     * @param data2 double[]
     **/
    @Contract(pure = true)
    public Covariance(@NotNull double[] data1, @NotNull double[] data2){
        this.data1 = data1;
        this.data2 = data2;
        this.n = data1.length;
    }

    @Contract(pure = true)
    public Covariance(@NotNull Double[] data1, @NotNull Double[] data2){
        double[] doubles = new double[data1.length];
        double[] doubles2 = new double[data2.length];
        for(int i = 0; i < data1.length; i++){ doubles[i] = data1[i]; }
        for(int i = 0; i < data2.length; i++){ doubles2[i] = data2[i]; }

        this.data1 = doubles;
        this.data2 = doubles2;
        this.n = data2.length;
    }

    private double covariance() throws DatasetNotMatchingException {
        double sum = 0;
        Average avg = new Average(data1);
        Average avg2 = new Average(data2);
        double avgMean = avg.calcMean();
        double avg2Mean = avg2.calcMean();

        if (data1.length != data2.length) {
            throw new DatasetNotMatchingException();
        }
        for (int i = 0; i < n; i++) {
            sum += ((data1[i] - avgMean) * (data2[i] - avg2Mean));
        }
        return sum;
    }

    public double calcCovarianceFromPopulation() throws Exception {
        return covariance() / n;
    }
    public double calcCovarianceFromSample() throws Exception {
        return covariance() / (n-1);
    }

}

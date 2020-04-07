package DOIFramework.statistics;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class relationshipDescribed implements  IRelationshipDescribed {

    private final double[] data1;
    private final double[] data2;
    private final double n;

    /**
     * @param data1 double[]
     * @param data2 double[]
     **/
    @Contract(pure = true)
    public relationshipDescribed(@NotNull double[] data1, @NotNull double[] data2){
        this.data1 = data1;
        this.data2 = data2;
        this.n = data1.length;
    }

    private double covariance() throws Exception {
        double sum = 0;
        Average avg = new Average(data1);
        Average avg2 = new Average(data2);
        if (data1.length != data2.length) {
            //TODO: fix exception here
            throw new Exception("Arrays must be same length");
        }
        for (int i = 0; i < n; i++) {
            sum = ((data1[i] - avg.calcMean()) * (data2[i] - avg2.calcMean()));
        }
        return sum;
    }

    @Override
    public double calcCovarianceFromPopulation() throws Exception {
        return covariance() / n;
    }

    @Override
    public double calcCovarianceFromSample() throws Exception {
        return covariance() / n-1;
    }

    @Override
    public double calcCorrelationCoefficientFromSample() {
        double sX = 0, sY = 0, sXY = 0, sXX = 0, sYY = 0;

        for (int i = 0; i < n; i++)
        {
            sX = sX + data1[i];
            sY = sY + data2[i];
            sXY = sXY + data1[i] * data2[i];
            sXX = sXX + data1[i] * data2[i];
            sYY = sYY + data2[i] * data2[i];
        }
        return (n * sXY - sX * sY) / Math.sqrt((n * sXX - sX * sX) * (n * sYY - sY * sY));
    }

    @Override
    public double calcStandardError() {
        return 0;
    }

    @Override
    public double covarianceToCorrelation() {
        return 0;
    }

}

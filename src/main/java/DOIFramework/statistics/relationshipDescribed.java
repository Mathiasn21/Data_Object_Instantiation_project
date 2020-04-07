package DOIFramework.statistics;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class relationshipDescribed implements  IRelationshipDescribed {

    private final double[] data1;
    private final double[] data2;

    /**
     * @param data1 double[]
     * @param data2 double[]
     **/
    @Contract(pure = true)
    public relationshipDescribed(@NotNull double[] data1, @NotNull double[] data2){
        this.data1 = data1;
        this.data2 = data2;
    }

    @Override
    public double calcCovarianceFromPopulation() {
        //TODO: implement method
        return 0;
    }

    @Override
    public double calcCovarianceFromSample() throws Exception {
        double sx = 0.0;
        double sy = 0.0;
        double sxy = 0.0;
        int n = data1.length;

        if(data1.length != data2.length){
            //TODO: fix exception here
            throw new Exception("Arrays must be same length");
        }
        else{
            for(int i = 0; i < n; ++i) {
                double x = data1[i];
                double y = data2[i];

                sx += x;
                sy += y;
                sxy += x * y;
            }
            return sxy / n - sx * sy / n / n;
        }
    }

    @Override
    public double calcCorrelationCoefficient() throws Exception {
        return calcCovarianceFromSample() / calcStandardErrorOf(data1) / calcStandardErrorOf(data2);
    }

    @Override
    public double calcStandardErrorOf(double[] data) throws Exception {
        double sx = 0.0;
        double sxx = 0.0;
        int n = data1.length;

        if(data1.length != data2.length){
            //TODO: fix exception here
            throw new Exception("Arrays must be same length");
        }
        else{
            for(int i = 0; i < n; ++i) {
                double x = data[i];

                sx += x;
                sxx += x * x;
            }
            return Math.sqrt(sxx / n -  sx * sx / n / n);
        }
    }
    public double calcStandardErrors() throws Exception {
        double x = calcStandardErrorOf(data1);
        double y = calcStandardErrorOf(data1);

        //TODO: finish method
        return 0;
    }

    @Override
    public double covarianceToCorrelation() {
        return 0;
    }

}

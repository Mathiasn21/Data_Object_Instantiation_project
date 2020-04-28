package doiframework.statistics.calculations;

import doiframework.statistics.report.IAdvancedReport;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


public final class TTest extends AdvancedStatistics  {

    private final Average avg;
    private final Average avg2;
    private final SimpleStatistics s1;
    private final SimpleStatistics s2;

    //This class has not been tested
    @Contract(pure = true)
    public TTest(@NotNull double[] data, @NotNull double[] data2) {
        super(data, data2);
        this.avg = new Average(data);
        this.avg2 = new Average(data2);
        this.s1 = new SimpleStatistics(data);
        this.s2 = new SimpleStatistics(data2);
    }
    @Contract(pure = true)
    public TTest(@NotNull Double[] data, @NotNull Double[] data2){
        super(data, data2);
        this.avg = new Average(data);
        this.avg2 = new Average(data2);
        this.s1 = new SimpleStatistics(data);
        this.s2 = new SimpleStatistics(data2);
    }
    @Contract(pure = true)
    public TTest(@NotNull Number[] data, @NotNull Number[] data2){
        super(data, data2);
        this.avg = new Average(data);
        this.avg2 = new Average(data2);
        this.s1 = new SimpleStatistics(data);
        this.s2 = new SimpleStatistics(data2);
    }

    public double unpaired(){
        double n1 = data.length;
        double n2 = data2.length;
        double meansDiff = avg.calcMean() - avg2.calcMean();
        double sP = Math.sqrt(unpairedVariance());

        return meansDiff / (sP * Math.sqrt(1/n1 + 1/n2));
    }

    private double unpairedVariance(){
        int n1 = data.length;
        int n2 = data2.length;
        double variance1 = s1.calcSampleVariance();
        double variance2 = s2.calcSampleVariance();

        return ((n1 -1)*Math.pow(variance1, 2) + (n2 - 1)*Math.pow(variance2, 2))/(n1 + n2 -2);
    }

    public double paired(){
        //TODO: implemented method
        return 0;
    }
}

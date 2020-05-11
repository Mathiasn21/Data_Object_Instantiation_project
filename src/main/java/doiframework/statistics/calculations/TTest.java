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
        double meansDiff = avg.calcMean() - avg2.calcMean();
        double sP = unpairedSTD();
        System.out.println("SP: " + sP);

        return meansDiff / Math.sqrt((unpairedSTD() * (1/n + 1/n2)));
    }

    private double unpairedSTD(){
        double mean1 = avg.calcMean();
        double mean2 = avg2.calcMean();
        double sqDiff1 = 0, sqDiff2 = 0;
        for (int i = 0; i < n; i++){
            sqDiff1 += Math.pow(data[i] - mean1, 2);
        }
        System.out.println(sqDiff1);
        for (int j = 0; j < n2; j++){
            sqDiff2 += Math.pow(data2[j] - mean2, 2);
        }

        return sqDiff1 + sqDiff2/(n + n2 -2);
    }

    public double paired(double significantLevel){
        //TODO: implemented method
        return 0;
    }
}

import doiframework.exceptions.DatasetNotMatchingException;
import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.statistics.calculations.Average;
import doiframework.statistics.calculations.Correlation;
import doiframework.statistics.calculations.Statistics;
import doiframework.statistics.report.DataReport;
import doiframework.statistics.report.Report;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NotPrimitiveNumberException, DatasetNotMatchingException {
        Double[] data = new Double[]{1d,2d,3d,4d,5d,6d,6d,6d};
        Double [] data2 = new Double[]{2d,5d,6d,7d,7d,8d,9d,6d};

        System.out.println("\n-------Testing Full Average Report with Execute Report-------\n");
        DataReport c1 = new DataReport(Report.getFullAverageReport(), data);
        var res1 = c1.executeReport();
        System.out.println(res1);

        System.out.println("\n-------Testing Full Simple Statistics Report with pretty print------\n");
        DataReport c3 = new DataReport(Report.getFullSimpleStatisticsReport(), data);
        c3.prettyPrintReport();

        System.out.println("\n-------Testing Full Covariance/Correlation Report with pretty print------\n");
        DataReport c4 = new DataReport(Report.getFullCovarianceCorrelationReport(), data, data2);
        c4.prettyPrintReport();

        System.out.println("\n-------Testing a simple calculation of correlation from population and sample------\n");
        Correlation c = new Correlation(data, data2);
        System.out.println(c.calcCorrelationCoefficientFromPopulation() + " " + c.calcCorrelationCoefficientFromSample());

        System.out.println("\n-------Testing making my own report with the Report builder and pretty printing ------\n");
        DataReport c5 = new DataReport(Report.getBuilder()
                .calcAverageMean()
                .calcAverageMedian()
                .calcAverageMidRange()
                .calcAverageMode()
                .calcSum()
                .calcSampleVariance()
                .calcPopulationVariance()
                .calcStandardDeviationFromSample()
                .calcStandardDeviationFromPopulation()
                .calcStandardErrorFromSample()
                .calcStandardErrorFromPopulation()
                .calcCovarianceFromSample()
                .calcCovarianceFromPopulation()
                .calcCorrelationFromPopulation()
                .calcCorrelationFromSample()
                .build(),
                data, data2);

        c5.prettyPrintReport();
    }
}

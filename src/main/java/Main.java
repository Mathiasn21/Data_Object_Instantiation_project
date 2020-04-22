import doiframework.exceptions.NotPrimitiveNumberException;
import doiframework.statistics.report.ReportCommand;
import doiframework.statistics.report.Report;

public class Main {
    public static void main(String[] args) throws NotPrimitiveNumberException {
        Double[] data = new Double[]{1d,2d,3d,4d,5d,6d,6d,6d};
        Double [] data2 = new Double[]{2d,5d,6d,7d,7d,8d,9d,6d};

        ReportCommand c1 = new ReportCommand(Report.getFullAverageReport(), data);
        var res1 = c1.executeReport();
        System.out.println(res1);

        ReportCommand c2 = new ReportCommand(Report.getFullSimpleStatisticsReport(), data);
        var res2 = c2.executeReport();
        System.out.println(res2);

        ReportCommand c3 = new ReportCommand(Report.getFullCovarianceCorrelationReport(), data, data2);
        var res3 = c3.executeReport();
        System.out.println(res3);
    }
}

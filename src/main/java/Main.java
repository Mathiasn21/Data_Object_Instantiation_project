import DOIFramework.exceptions.NotPrimitiveNumber;
import DOIFramework.statistics.report.CentralCommand;
import DOIFramework.statistics.report.ReportThings;

public class Main {
    public static void main(String[] args) throws NotPrimitiveNumber {
        Double[] data = new Double[]{1d,2d,3d,4d,5d,6d,6d,6d};

        CentralCommand c1 = new CentralCommand(ReportThings.getFullAverageReport(), data);
        var res1 = c1.executeReport();
        System.out.println(res1);

        CentralCommand c2 = new CentralCommand(ReportThings.getFullReportSimpleStatistics(), data);
        var res2 = c2.executeReport();
        System.out.println(res2);
    }
}

package doiframework.statistics.report;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum Report {
    AVERAGE_MEAN(AverageReport.AVERAGE_MEAN),
    AVERAGE_MID_RANGE(AverageReport.AVERAGE_MID_RANGE),
    AVERAGE_MEDIAN(AverageReport.AVERAGE_MEDIAN),
    AVERAGE_MODE(AverageReport.AVERAGE_MODE),
    TOTAL_SUM(AverageReport.AVERAGE_SUM),

    POPULATION_VARIANCE(SimpleStatisticalReport.POPULATION_VARIANCE),
    SAMPLE_VARIANCE(SimpleStatisticalReport.SAMPLE_VARIANCE),
    STANDARD_DEVIATION_POPULATION(SimpleStatisticalReport.STANDARD_DEVIATION_POPULATION),
    STANDARD_DEVIATION_SAMPLE(SimpleStatisticalReport.STANDARD_DEVIATION_SAMPLE),
    STANDARD_ERROR_SAMPLE(SimpleStatisticalReport.STANDARD_ERROR_SAMPLE),
    STANDARD_ERROR_POPULATION(SimpleStatisticalReport.STANDARD_ERROR_POPULATION),

    COVARIANCE_SAMPLE(CovarianceReport.COVARIANCE_SAMPLE),
    COVARIANCE_POPULATION(CovarianceReport.COVARIANCE_POPULATION),
    CORRELATION_SAMPLE(CorrelationReport.CORRELATION_SAMPLE),
    CORRELATION_POPULATION(CorrelationReport.CORRELATION_POPULATION);

    private final IReport report;

    Report(IReport report) {
        this.report = report;
    }

    @Override
    @NotNull
    public String toString() {
        return this.report.getOption();
    }

    public IReport getIReport(){
        return report;
    }

    @Contract(value = " -> new", pure = true)
    public static Report @NotNull [] getFullAverageReport(){
        return new Report[]{
                Report.AVERAGE_MEAN,
                Report.AVERAGE_MID_RANGE,
                Report.AVERAGE_MEDIAN,
                Report.TOTAL_SUM,
                Report.AVERAGE_MODE
        };
    }

    @Contract(value = " -> new", pure = true)
    public static Report @NotNull [] getFullSimpleStatisticsReport(){
        return new Report[]{
                Report.SAMPLE_VARIANCE,
                Report.POPULATION_VARIANCE,
                Report.STANDARD_DEVIATION_SAMPLE,
                Report.STANDARD_DEVIATION_POPULATION,
                Report.STANDARD_ERROR_SAMPLE,
                Report.STANDARD_ERROR_POPULATION
        };
    }

    @Contract(value = " -> new", pure = true)
    public static Report @NotNull [] getFullCovarianceCorrelationReport(){
        return new Report[]{
                Report.COVARIANCE_SAMPLE,
                Report.COVARIANCE_POPULATION,
                Report.CORRELATION_SAMPLE,
                Report.CORRELATION_POPULATION
        };
    }

    public static ReportBuilder getBuilder(){
        return new ReportBuilder();
    }

}

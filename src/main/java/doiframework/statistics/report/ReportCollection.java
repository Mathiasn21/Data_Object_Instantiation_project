package doiframework.statistics.report;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum ReportCollection {
    AVERAGE_MEAN(AverageReportStrategy.AVERAGE_MEAN),
    AVERAGE_MID_RANGE(AverageReportStrategy.AVERAGE_MID_RANGE),
    AVERAGE_MEDIAN(AverageReportStrategy.AVERAGE_MEDIAN),
    AVERAGE_MODE(AverageReportStrategy.AVERAGE_MODE),
    TOTAL_SUM(AverageReportStrategy.AVERAGE_SUM),

    POPULATION_VARIANCE(SimpleStatisticalReport.POPULATION_VARIANCE),
    SAMPLE_VARIANCE(SimpleStatisticalReport.SAMPLE_VARIANCE),
    STANDARD_DEVIATION_POPULATION(SimpleStatisticalReport.STANDARD_DEVIATION_POPULATION),
    STANDARD_DEVIATION_SAMPLE(SimpleStatisticalReport.STANDARD_DEVIATION_SAMPLE),
    STANDARD_ERROR_SAMPLE(SimpleStatisticalReport.STANDARD_ERROR_SAMPLE),
    STANDARD_ERROR_POPULATION(SimpleStatisticalReport.STANDARD_ERROR_POPULATION),

    COVARIANCE_SAMPLE(CovarianceReportStrategy.COVARIANCE_SAMPLE),
    COVARIANCE_POPULATION(CovarianceReportStrategy.COVARIANCE_POPULATION),
    CORRELATION_SAMPLE(CorrelationReportStrategy.CORRELATION_SAMPLE),
    CORRELATION_POPULATION(CorrelationReportStrategy.CORRELATION_POPULATION);

    private final IReportContext report;

    ReportCollection(IReportContext report) {
        this.report = report;
    }

    @Override
    @NotNull
    public String toString() {
        return this.report.getOption();
    }

    public IReportContext getIReport(){
        return report;
    }

    @Contract(value = " -> new", pure = true)
    public static ReportCollection @NotNull [] getFullAverageReport(){
        return new ReportCollection[]{
                ReportCollection.AVERAGE_MEAN,
                ReportCollection.AVERAGE_MID_RANGE,
                ReportCollection.AVERAGE_MEDIAN,
                ReportCollection.TOTAL_SUM,
                ReportCollection.AVERAGE_MODE
        };
    }

    @Contract(value = " -> new", pure = true)
    public static ReportCollection @NotNull [] getFullSimpleStatisticsReport(){
        return new ReportCollection[]{
                ReportCollection.SAMPLE_VARIANCE,
                ReportCollection.POPULATION_VARIANCE,
                ReportCollection.STANDARD_DEVIATION_SAMPLE,
                ReportCollection.STANDARD_DEVIATION_POPULATION,
                ReportCollection.STANDARD_ERROR_SAMPLE,
                ReportCollection.STANDARD_ERROR_POPULATION
        };
    }

    @Contract(value = " -> new", pure = true)
    public static ReportCollection @NotNull [] getFullCovarianceCorrelationReport(){
        return new ReportCollection[]{
                ReportCollection.COVARIANCE_SAMPLE,
                ReportCollection.COVARIANCE_POPULATION,
                ReportCollection.CORRELATION_SAMPLE,
                ReportCollection.CORRELATION_POPULATION
        };
    }

    public static ReportBuilder getBuilder(){
        return new ReportBuilder();
    }

}

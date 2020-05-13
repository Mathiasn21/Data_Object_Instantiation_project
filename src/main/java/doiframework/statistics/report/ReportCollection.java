package doiframework.statistics.report;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum ReportCollection {
    AVERAGE_MEAN(AverageReportStrategy.AVERAGE_MEAN),
    AVERAGE_MID_RANGE(AverageReportStrategy.AVERAGE_MID_RANGE),
    AVERAGE_MEDIAN(AverageReportStrategy.AVERAGE_MEDIAN),
    AVERAGE_MODE(AverageReportStrategy.AVERAGE_MODE),
    TOTAL_SUM(AverageReportStrategy.AVERAGE_SUM),

    POPULATION_VARIANCE(SimpleStatisticalReportStrategy.POPULATION_VARIANCE),
    SAMPLE_VARIANCE(SimpleStatisticalReportStrategy.SAMPLE_VARIANCE),
    STANDARD_DEVIATION_POPULATION(SimpleStatisticalReportStrategy.STANDARD_DEVIATION_POPULATION),
    STANDARD_DEVIATION_SAMPLE(SimpleStatisticalReportStrategy.STANDARD_DEVIATION_SAMPLE),
    STANDARD_ERROR_SAMPLE(SimpleStatisticalReportStrategy.STANDARD_ERROR_SAMPLE),
    STANDARD_ERROR_POPULATION(SimpleStatisticalReportStrategy.STANDARD_ERROR_POPULATION),

    COVARIANCE_SAMPLE(CovarianceReportStrategy.COVARIANCE_SAMPLE),
    COVARIANCE_POPULATION(CovarianceReportStrategy.COVARIANCE_POPULATION),
    CORRELATION_SAMPLE(CorrelationReportStrategy.CORRELATION_SAMPLE),
    CORRELATION_POPULATION(CorrelationReportStrategy.CORRELATION_POPULATION);

    private final IReport report;

    ReportCollection(IReport report) {
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

    @NotNull
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

    @NotNull
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

    @NotNull
    @Contract(value = " -> new", pure = true)
    public static ReportCollection @NotNull [] getFullCovarianceCorrelationReport(){
        return new ReportCollection[]{
                ReportCollection.COVARIANCE_SAMPLE,
                ReportCollection.COVARIANCE_POPULATION,
                ReportCollection.CORRELATION_SAMPLE,
                ReportCollection.CORRELATION_POPULATION
        };
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public static ReportBuilder getBuilder(){
        return new ReportBuilder();
    }

}

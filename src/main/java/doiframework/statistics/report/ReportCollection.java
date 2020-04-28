package doiframework.statistics.report;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum ReportCollection {
    AVERAGE_MEAN(AverageStrategy.AVERAGE_MEAN),
    AVERAGE_MID_RANGE(AverageStrategy.AVERAGE_MID_RANGE),
    AVERAGE_MEDIAN(AverageStrategy.AVERAGE_MEDIAN),
    AVERAGE_MODE(AverageStrategy.AVERAGE_MODE),
    TOTAL_SUM(AverageStrategy.AVERAGE_SUM),

    POPULATION_VARIANCE(SimpleStatisticalStrategy.POPULATION_VARIANCE),
    SAMPLE_VARIANCE(SimpleStatisticalStrategy.SAMPLE_VARIANCE),
    STANDARD_DEVIATION_POPULATION(SimpleStatisticalStrategy.STANDARD_DEVIATION_POPULATION),
    STANDARD_DEVIATION_SAMPLE(SimpleStatisticalStrategy.STANDARD_DEVIATION_SAMPLE),
    STANDARD_ERROR_SAMPLE(SimpleStatisticalStrategy.STANDARD_ERROR_SAMPLE),
    STANDARD_ERROR_POPULATION(SimpleStatisticalStrategy.STANDARD_ERROR_POPULATION),

    COVARIANCE_SAMPLE(CovarianceStrategy.COVARIANCE_SAMPLE),
    COVARIANCE_POPULATION(CovarianceStrategy.COVARIANCE_POPULATION),
    CORRELATION_SAMPLE(CorrelationStrategy.CORRELATION_SAMPLE),
    CORRELATION_POPULATION(CorrelationStrategy.CORRELATION_POPULATION);

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

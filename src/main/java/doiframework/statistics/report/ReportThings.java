package doiframework.statistics.report;

import org.jetbrains.annotations.NotNull;

public enum ReportThings {
    //TODO: add all calculations here
    AVERAGE_MEAN(AverageReport.AVERAGE_MEAN),
    AVERAGE_MID_RANGE(AverageReport.AVERAGE_MID_RANGE),
    AVERAGE_MEDIAN(AverageReport.AVERAGE_MEDIAN),
    AVERAGE_MODE(AverageReport.AVERAGE_MODE),
    AVERAGE_SUM(AverageReport.AVERAGE_SUM),

    POPULATION_VARIANCE(SimpleStatisticalReport.SAMPLE_VARIANCE),
    SAMPLE_VARIANCE(SimpleStatisticalReport.SAMPLE_VARIANCE),
    STANDARD_DEVIATION_POPULATION(SimpleStatisticalReport.STANDARD_DEVIATION_POPULATION),
    STANDARD_DEVIATION_SAMPLE(SimpleStatisticalReport.STANDARD_DEVIATION_SAMPLE),
    STANDARD_ERROR_SAMPLE(SimpleStatisticalReport.STANDARD_ERROR_SAMPLE),
    STANDARD_ERROR_POPULATION(SimpleStatisticalReport.STANDARD_ERROR_POPULATION);


    private final IReport report;
    ReportThings(IReport report) {
        this.report = report;
    }

    @Override
    @NotNull
    public String toString() {
        return "ReportThings{}";
    }

    public IReport getIReport(){
        return report;
    }

    public static ReportThings[] getFullAverageReport(){
        return new ReportThings[]{
                ReportThings.AVERAGE_MEAN,
                ReportThings.AVERAGE_MID_RANGE,
                ReportThings.AVERAGE_MEDIAN,
                ReportThings.AVERAGE_SUM,
                ReportThings.AVERAGE_MODE
        };
    }

    public static ReportThings[] getFullReportSimpleStatistics(){
        return new ReportThings[]{
                ReportThings.SAMPLE_VARIANCE,
                ReportThings.POPULATION_VARIANCE,
                ReportThings.STANDARD_DEVIATION_SAMPLE,
                ReportThings.STANDARD_DEVIATION_POPULATION,
                ReportThings.STANDARD_ERROR_SAMPLE,
                ReportThings.STANDARD_ERROR_POPULATION
        };
    }
}

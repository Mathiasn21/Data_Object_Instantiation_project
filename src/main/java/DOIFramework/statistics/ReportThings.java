package DOIFramework.statistics;

import DOIFramework.core.extractors.AverageReport;
import DOIFramework.extractors.IReport;
import org.jetbrains.annotations.NotNull;

public enum ReportThings {
    AVERAGE_MEAN(AverageReport.AVERAGE_MEAN);

    private final IReport report;
    ReportThings(IReport report) {
        this.report = report;
    }

    @Override
    @NotNull
    public String toString() {
        return "ReportThings{}";
    }

    IReport getIReport(){
        return report;
    }
}

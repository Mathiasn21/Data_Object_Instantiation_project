package framework.extractors;

import framework.statistics.IAverage;

public enum ReportOptions {
    AVERAGE("Average", IAverage::calcMean);

    public final String option;
    final Calculate calculate;

    ReportOptions(String option, Calculate calculate) {
        this.option = option;
        this.calculate = calculate;
    }

    @Override
    public String toString() { return option; }
}

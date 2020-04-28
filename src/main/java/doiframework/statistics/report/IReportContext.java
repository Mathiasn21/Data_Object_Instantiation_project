package doiframework.statistics.report;

import doiframework.statistics.calculations.Statistics;

public interface IReportContext {
    Class<? extends Statistics> getStatisticalClass();
    String getOption();
    double calculate(Statistics statistics) throws Exception;
}

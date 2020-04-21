package doiframework.statistics.report;

import doiframework.statistics.calculations.Statistics;

public interface IReport {
    Class<? extends Statistics> getMainClass();
    String getOption();
    double calculate(Statistics statistics);
}

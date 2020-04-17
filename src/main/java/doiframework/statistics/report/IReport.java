package doiframework.statistics.report;

import doiframework.statistics.calculations.IStatistics;

public interface IReport {
    Class<? extends IStatistics> getMainClass();
}

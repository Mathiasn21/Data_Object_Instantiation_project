package DOIFramework.statistics.report;

import DOIFramework.statistics.calculations.IStatistics;

public interface IReport {
    Class<? extends IStatistics> getMainClass();
}

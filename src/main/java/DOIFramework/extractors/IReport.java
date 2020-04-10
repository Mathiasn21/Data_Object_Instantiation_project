package DOIFramework.extractors;

import DOIFramework.statistics.IStatistics;

public interface IReport {
    Class<? extends IStatistics> getMainClass();
}

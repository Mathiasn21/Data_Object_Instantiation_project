package DOIFramework.core.extractors;

import DOIFramework.statistics.IStatistics;

public interface IReport {
    Class<? extends IStatistics> getMainClass();
}

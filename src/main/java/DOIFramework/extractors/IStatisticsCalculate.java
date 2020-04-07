package DOIFramework.extractors;

import DOIFramework.statistics.IStatistics;

/**
 * A interface for wrapping statistics calculation.
 * {@link IStatistics}
 */
@FunctionalInterface
interface IStatisticsCalculate {
    double execute(IStatistics statistics);
}

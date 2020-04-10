package DOIFramework.extractors;

import DOIFramework.statistics.SimpleStatistics;

/**
 * A interface for wrapping statistics calculation.
 * {@link IStatistics}
 */
@FunctionalInterface
interface IStatisticsCalculate {
    double execute(SimpleStatistics statistics);
}

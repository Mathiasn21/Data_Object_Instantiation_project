package doiframework.statistics.calculations;

/**
 * A interface for wrapping statistics calculation.
 * {@link IStatistics}
 */
@FunctionalInterface
public interface IStatisticsCalculate {
    double execute(SimpleStatistics statistics);
}

package doiframework.statistics.calculations;

/**
 * A interface for wrapping statistics calculation.
 * {@link Statistics}
 */
@FunctionalInterface
public interface IStatisticsCalculate {
    double execute(SimpleStatistics statistics);
}

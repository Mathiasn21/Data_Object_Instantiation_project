package doiframework.statistics.calculations;
/**
 * A interface for wrapping statistics calculation.
 * {@link Statistics}
 */
@FunctionalInterface
public interface ICorrelationCalculate {
    double execute(Correlation statistics) throws Exception;
}

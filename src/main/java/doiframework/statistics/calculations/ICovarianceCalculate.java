package doiframework.statistics.calculations;

/**
 * A interface for wrapping statistics calculation.
 * {@link Statistics}
 */
@FunctionalInterface
public interface ICovarianceCalculate {
        double execute(Covariance statistics) throws Exception;
}


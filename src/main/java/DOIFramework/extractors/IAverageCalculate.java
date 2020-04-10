package DOIFramework.extractors;

import DOIFramework.statistics.Average;

/**
 * An interface used for wrapping Average calculation.
 * {@link Average}
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
@FunctionalInterface
interface IAverageCalculate {
    double execute(Average statistics);
}

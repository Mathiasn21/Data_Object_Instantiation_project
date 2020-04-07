package DOIFramework.extractors;

import DOIFramework.statistics.IAverage;

/**
 * An interface used for wrapping Average calculation.
 * {@link IAverage}
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
@FunctionalInterface
interface IAverageCalculate {
    double execute(IAverage statistics);
}

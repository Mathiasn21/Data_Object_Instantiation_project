package framework.extractors;

import framework.statistics.IAverage;

/**
 * An interface used for wrapping statistics calculation
 */
@FunctionalInterface
interface Calculate {
    double execute(IAverage statistics);
}

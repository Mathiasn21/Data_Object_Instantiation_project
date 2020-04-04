package framework.extractors;

import framework.statistics.IAverage;

@FunctionalInterface
interface Calculate {
    double execute(IAverage statistics);
}

package framework.statistics;

/**
 * Class for calculating statistics from data
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public interface IStatistics {

    double sampleVariance();
    double populationVariance();
    double standardDeviationFromSample();
    double standardDeviationFromPopulation();
}

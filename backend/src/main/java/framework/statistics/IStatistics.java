package framework.statistics;

/**
 * Class for calculating statistics execute data
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public interface IStatistics {

    double calcSampleVariance();
    double calcPopulationVariance();
    double calcStandardDeviationFromSample();
    double calcStandardDeviationFromPopulation();
    double calcCovariance();
    double calcCorrelation();
}

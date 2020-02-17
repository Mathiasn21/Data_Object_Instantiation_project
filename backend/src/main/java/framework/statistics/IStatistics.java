package framework.statistics;

public interface IStatistics {

    double averageMean();
    double calcMedian();
    double averageMode();
    double averageMidRange();
    double sampleVariance();
    double populationVariance();
    double standardDeviationFromSample();
    double standardDeviationFromPopulation();

}

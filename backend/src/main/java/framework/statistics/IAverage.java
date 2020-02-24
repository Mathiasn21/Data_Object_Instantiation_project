package framework.statistics;

/**
 * Class for calculating average-calculations from data
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public interface IAverage {
    double calcSum();
    double averageMean();
    double median();
    double averageMode();
    double averageMidRange();
}

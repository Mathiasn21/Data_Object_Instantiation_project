package DOIFramework.statistics;

//TODO: find a better name for class.
public interface IRelationshipDescribed {
    double calcCovarianceFromPopulation();
    double calcCovarianceFromSample(double[] data1, double[] data2) throws Exception;
    double calcCorrelationCoefficient();
    double calcStandardError();
    double covarianceToCorrelation();
}

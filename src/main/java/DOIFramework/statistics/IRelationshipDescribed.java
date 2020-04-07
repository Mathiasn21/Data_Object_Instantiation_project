package DOIFramework.statistics;

//TODO: find a better name for class.

public interface IRelationshipDescribed {
    double calcCovarianceFromPopulation();
    double calcCovarianceFromSample() throws Exception;
    double calcCorrelationCoefficient() throws Exception;
    double calcStandardErrorOf(double[] data) throws Exception;
    double calcStandardErrors() throws Exception;
    double covarianceToCorrelation();

}

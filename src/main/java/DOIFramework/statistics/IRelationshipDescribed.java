package DOIFramework.statistics;

//TODO: find a better name for class.
public interface IRelationshipDescribed {
    double calcCovarianceFromPopulation() throws Exception;
    double calcCovarianceFromSample() throws Exception;
    double calcCorrelationCoefficientFromSample();
    double calcStandardError();
    double covarianceToCorrelation();
}

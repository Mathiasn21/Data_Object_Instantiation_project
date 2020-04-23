package doiframework.statistics.calculations;

public final class PoissonDistribution {

    double lambda;
    double t;
    public PoissonDistribution(double lambda, double t){
        this.lambda = lambda;
        this.t = t;
    }

    public double calcExcpectedValue(){
        return lambda * t;
    }

    public double calcVariance(){
        return lambda * t;
    }

    public double calcPoissonDistribution(int variableX){
        double expectedValue = calcExcpectedValue();
        Permutations p = new Permutations();
        int factorialX = p.factorial(variableX);
        return (Math.pow(expectedValue, variableX) / factorialX) * Math.exp(-expectedValue);
    }

    public double calcCumulativeProbabilityLessThanEqual(int variableX){
        double expectedValue = calcExcpectedValue();
        double poissonValue = 0;
        for(int i = variableX; i >= 0; i--){
                poissonValue += calcPoissonDistribution(variableX);
                variableX--;
        }
        return poissonValue;
    }
    public double calcCumulativeProbabilityMoreThan(int variableX){
        double expectedValue = calcExcpectedValue();
        double poissonValue = 0;
        for(int i = variableX; i >= 0; i--){
            poissonValue += calcPoissonDistribution(variableX);
            variableX--;
        }
        return 1 - poissonValue;
    }
    public double calcCumulativeProbabilityMoreThanEqual(int variableX){
        double expectedValue = calcExcpectedValue();
        double cdf = calcCumulativeProbabilityLessThanEqual(variableX - 1);
        return 1 - cdf;
    }
}

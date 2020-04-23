package doiframework.statistics.calculations;

public final class PoissonDistribution {

    public PoissonDistribution(){
    }

    public double calcExcpectedValue(double lambda, double t){
        return lambda * t;
    }

    public double calcVariance(double lambda, double t){
        return lambda * t;
    }

    public double calcPoissonDistribution(int variableX, double expectedValue){
        Permutations p = new Permutations();
        int factorialX = p.factorial(variableX);
        return (Math.pow(expectedValue, variableX) / factorialX) * Math.exp(-expectedValue);
    }

    public double calcCumulativeProbabilityLessThanEqual(int variableX, double expectedValue){
        double poissonValue = 0;
        for(int i = variableX; i >= 0; i--){
                poissonValue += calcPoissonDistribution(variableX, expectedValue);
                variableX--;
        }
        return poissonValue;
    }
    public double calcCumulativeProbabilityMoreThan(int variableX, double expectedValue){
        double poissonValue = 0;
        for(int i = variableX; i >= 0; i--){
            poissonValue += calcPoissonDistribution(variableX, expectedValue);
            variableX--;
        }
        return 1 - poissonValue;
    }
    public double calcCumulativeProbabilityMoreThanEqual(int variableX, double expectedValue){
        double cdf = calcCumulativeProbabilityLessThanEqual(variableX - 1, expectedValue);
        return 1 - cdf;
    }
}

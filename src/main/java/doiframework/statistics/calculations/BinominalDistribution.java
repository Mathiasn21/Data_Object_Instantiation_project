package doiframework.statistics.calculations;

public final class BinominalDistribution {
    private int numberOfAttempts;
    private double probability;

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public double getProbability() {
        return probability;
    }

    public BinominalDistribution(int numberOfAttempts, double probability) {

        if(numberOfAttempts <=0){
            throw new IllegalArgumentException("n has to be greater than 0");
        }
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("The probability has to be between 0 and 1");
        }
        this.numberOfAttempts = numberOfAttempts;
        this.probability = probability;
    }

    public double calcBinominalProbability(int variableX){
        if (variableX > numberOfAttempts){
            throw new IllegalArgumentException("X has to be less than number of attempts");
        }
        Combinations comb = new Combinations();
        int binonminalCoeff = comb.binominalCoefficient(numberOfAttempts, variableX);

        return binonminalCoeff * Math.pow(probability, variableX)*
                Math.pow((1 - probability), (numberOfAttempts - variableX));
    }

    public double calcExcpectedValue(){
        return numberOfAttempts * probability;
    }

    public double calcVariance(){
        return numberOfAttempts * probability*(1-probability);
    }

    public double calCumulativeProbabilityLessThanEqual(int variableX){
        if (variableX > numberOfAttempts){
            throw new IllegalArgumentException("X has to be less than number of attempts");
        }
        double binominalPorbability = 0;
        for(int i = 0; i <= numberOfAttempts; i++){
            if(variableX < 0){
                break;
            }else {
                binominalPorbability += calcBinominalProbability(variableX);
                variableX--;
            }
        }
        return binominalPorbability;
    }

    public double calCumulativeProbabilityMoreThan(int variableX){
        if (variableX > numberOfAttempts){
            throw new IllegalArgumentException("X has to be less than number of attempts");
        }
        double binominalPorbability = 0;
        for(int i = 0; i <= numberOfAttempts; i++){
            if(variableX < 0){
                break;
            }else {
                binominalPorbability += calcBinominalProbability(variableX);
                variableX--;
            }
        }
        return 1 - binominalPorbability;
    }

    public double calCumulativeProbabilityMoreThanEqual(int variableX){
        if (variableX > numberOfAttempts){
            throw new IllegalArgumentException("X has to be less than number of attempts");
        }
        double cdf = calCumulativeProbabilityLessThanEqual(variableX - 1);
        return 1 - cdf;
    }

}

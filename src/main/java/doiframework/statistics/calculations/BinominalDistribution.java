package doiframework.statistics.calculations;

public class BinominalDistribution {
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
        numberOfAttempts = this.numberOfAttempts;
        probability = this.probability;
    }

    public double calcBinominalDistributionOfVariable(int variable, int numberOfAttempts, double probability){
        Combinations comb = new Combinations(numberOfAttempts, variable);
        int binonminalCoeff = comb.withoutReputition();

        return binonminalCoeff * Math.pow(probability, variable)*
                (Math.pow(1 - probability, numberOfAttempts - variable));
    }

    public double calcBinominalExcpectedValue(int numberOfAttempts, double probability){
        if(numberOfAttempts <=0){
            throw new IllegalArgumentException("n has to be greater than 0");
        }
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("The probability has to be between 0 and 1");
        }
        return numberOfAttempts * probability;
    }
    public double calcBinominalVariance(int numberOfAttempts, double probability){
        if(numberOfAttempts <=0){
            throw new IllegalArgumentException("n has to be greater than 0");
        }
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("The probability has to be between 0 and 1");
        }
        return numberOfAttempts * probability*(1-probability);
    }
}

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
        this.numberOfAttempts = numberOfAttempts;
        this.probability = probability;
    }

    public double calcBinominalProbability(int variable){
        if(numberOfAttempts <=0){
            throw new IllegalArgumentException("n has to be greater than 0");
        }
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("The probability has to be between 0 and 1");
        }
        Combinations comb = new Combinations(numberOfAttempts, variable);
        int binonminalCoeff = comb.withoutReputition();

        return binonminalCoeff * Math.pow(probability, variable)*
                (Math.pow(1 - probability, numberOfAttempts - variable));
    }

    public double calcBinominalExcpectedValue(){
        if(numberOfAttempts <=0){
            throw new IllegalArgumentException("n has to be greater than 0");
        }
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("The probability has to be between 0 and 1");
        }
        return numberOfAttempts * probability;
    }

    public double calcBinominalVariance(){
        if(numberOfAttempts <=0){
            throw new IllegalArgumentException("n has to be greater than 0");
        }
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("The probability has to be between 0 and 1");
        }
        return numberOfAttempts * probability*(1-probability);
    }

    public double calcBinominalCumulativeProbability(int variable){
        //TODO: implement method
        return 0;
    }
}

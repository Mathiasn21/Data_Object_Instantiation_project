package doiframework.statistics.calculations;

public final class Combinations {

    public Combinations(){}

    private int factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    public int withRepetition(int n, int elementsChosen) {
        return factorial(elementsChosen + n - 1) / (factorial(elementsChosen) *
                factorial(n - 1));
    }

    public int binominalCoefficient(int n, int elementsChosen) {
        if ((n == elementsChosen) || (elementsChosen == 0))
            return 1;
        else
            return binominalCoefficient(n - 1, elementsChosen) +
                    binominalCoefficient(n - 1, elementsChosen - 1);
    }
}
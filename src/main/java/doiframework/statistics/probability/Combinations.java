package doiframework.statistics.probability;

public final class Combinations {

    private int factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    public int withRepetition(int n, int elementsChosen) {
        return factorial(elementsChosen + n - 1) / (factorial(elementsChosen) *
                factorial(n - 1));
    }

    public int withoutRepetition(int n, int elementsChosen) {
        if ((n == elementsChosen) || (elementsChosen == 0))
            return 1;
        else
            return withoutRepetition(n - 1, elementsChosen) +
                    withoutRepetition(n - 1, elementsChosen - 1);
    }
}
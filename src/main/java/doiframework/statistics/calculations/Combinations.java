package doiframework.statistics.calculations;

public final class Combinations {
    int n;
    int elementsChosen;

    public Combinations(int n, int elementsChosen){
        this.n = n;
        this.elementsChosen = elementsChosen;
    }

    private int factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    public int withReputition() {
        return factorial(elementsChosen + n - 1) / (factorial(elementsChosen) *
                factorial(n - 1));
    }

    //binominal coefficient
    public int withoutReputition() {
        return factorial(n) / (factorial(elementsChosen) * factorial(n - elementsChosen));
    }
}
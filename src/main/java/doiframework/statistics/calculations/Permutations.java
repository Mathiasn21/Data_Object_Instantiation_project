package doiframework.statistics.calculations;

public final class Permutations {

    public Permutations() {}

    private int factorial(int n){
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    public double withRepetition(int n, int repetitions){
        return Math.pow(n, repetitions);
    }

    public int withoutRepetition(int n, int repetitions){
        return factorial(n) / factorial(n - repetitions);
    }
}

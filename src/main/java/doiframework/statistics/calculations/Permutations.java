package doiframework.statistics.calculations;

public final class Permutations {

    int n;
    int reputitions;

    public Permutations(int n, int reputitions) {
        this.n = n;
        this.reputitions = reputitions;
    }

    private int factorial(int n){
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    public double withRepition(){
        return Math.pow(n, reputitions);
    }

    public int withoutRepition(){
        return factorial(n) / factorial(n - reputitions);
    }

}

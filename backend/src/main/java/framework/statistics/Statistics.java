package framework.statistics;



public class Statistics implements IStatistics {

    double[] data;

    public Statistics(double[] data){
        this.data = data;
    }

    public double calcAverageMean() {
        double[] values = data;
        double total = 0;

        for(double i : values){
            total += i;
        }
        double averageMean = total / values.length;

        return averageMean;
    }

    @Override
    public String toString() {
        return "Statistics calculation";
    }
}

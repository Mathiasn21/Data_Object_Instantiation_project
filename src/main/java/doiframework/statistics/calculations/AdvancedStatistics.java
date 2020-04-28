package doiframework.statistics.calculations;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdvancedStatistics extends Statistics {
    protected final double[] data2;
    protected final double n;

    public AdvancedStatistics(@NotNull double[] data1, @NotNull double[] data2) {
        super(data1);
        this.data2 = data2;
        this.n = data1.length;
    }
    public AdvancedStatistics(@NotNull Double[] data1, @NotNull Double[] data2){
        super(data1);
        double[] doubles2 = new double[data2.length];
        for(int i = 0; i < data2.length; i++){ doubles2[i] = data2[i]; }

        this.data2 = doubles2;
        this.n = data1.length;
    }
    public AdvancedStatistics(@NotNull Number[] data1, @NotNull Number[] data2){
        super(data1);
        double[] doubles2 = new double[data2.length];
        for(int i = 0; i < data2.length; i++){ doubles2[i] = data2[i].doubleValue(); }

        this.data2 = doubles2;
        this.n = data1.length;
    }
    public AdvancedStatistics(@NotNull List<Number> data1, @NotNull List<Number> data2){
        super(data1);
        double[] doubles2 = new double[data2.size()];
        for(int i = 0; i < data2.size(); i++){ doubles2[i] = data2.get(i).doubleValue(); }

        this.data2 = doubles2;
        this.n = data1.size();
    }
}

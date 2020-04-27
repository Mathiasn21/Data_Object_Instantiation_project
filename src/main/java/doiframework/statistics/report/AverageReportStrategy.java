package doiframework.statistics.report;

import java.util.Arrays;
import java.util.List;

import doiframework.statistics.calculations.Average;
import doiframework.statistics.calculations.IAverageCalculate;
import doiframework.statistics.calculations.Statistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public enum AverageReportStrategy implements IReportContext {
    AVERAGE_SUM("Total sum", Average::calcSum),
    AVERAGE_MEAN("Average", Average::calcMean),
    AVERAGE_MEDIAN("Median", Average::calcMedian),
    AVERAGE_MID_RANGE("Mid range", Average::calcMidRange),
    AVERAGE_MODE("Mode", Average::calcMode);

    public final String option;
    public final IAverageCalculate calculate;
    private static final Class<? extends Statistics> clazz = Average.class;

    @Contract(pure = true)
    AverageReportStrategy(String option, IAverageCalculate calculate) {
        this.option = option;
        this.calculate = calculate;
    }

    /**
     * @return String - option
     */
    @Contract(pure = true)
    @Override
    public String toString() { return option; }

    /**
     * @return {@link List}&lt;{@link AverageReportStrategy}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<AverageReportStrategy> getStandardConfiguration(){
        return Arrays.asList(AverageReportStrategy.values());
    }

    @Override
    public Class<? extends Statistics> getMainClass() {
        return clazz;
    }

    @Override
    public String getOption() {
        return this.option;
    }

    @Override
    public double calculate(Statistics statistics) {
        double d = -1;
        if(statistics instanceof Average){
            d = calculate.execute((Average) statistics);
        }
        return d;
    }

}

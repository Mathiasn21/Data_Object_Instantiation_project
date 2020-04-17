package doiframework.statistics.report;

import java.util.Arrays;
import java.util.List;

import doiframework.statistics.calculations.Average;
import doiframework.statistics.calculations.IAverageCalculate;
import doiframework.statistics.calculations.IStatistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public enum AverageReport implements IReport {
    AVERAGE_SUM("Total sum", Average::calcSum),
    AVERAGE_MEAN("Average", Average::calcMean),
    AVERAGE_MEDIAN("Median", Average::calcMedian),
    AVERAGE_MID_RANGE("Mid range", Average::calcMidRange),
    AVERAGE_MODE("Mode", Average::calcMode);

    public final String option;
    public final IAverageCalculate calculate;
    private static Class<? extends IStatistics> clazz = Average.class;

    @Contract(pure = true)
    AverageReport(String option, IAverageCalculate calculate) {
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
     * @return {@link List}&lt;{@link AverageReport}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<AverageReport> getStandardConfiguration(){
        return Arrays.asList(AverageReport.values());
    }

    @Override
    public Class<? extends IStatistics> getMainClass() {
        return clazz;
    }

}

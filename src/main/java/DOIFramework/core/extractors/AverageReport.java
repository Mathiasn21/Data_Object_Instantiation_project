package DOIFramework.core.extractors;

import DOIFramework.statistics.IAverage;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public enum AverageReport {
    AVERAGE_SUM("Total sum", IAverage::calcSum),
    AVERAGE_MEAN("Average", IAverage::calcMean),
    AVERAGE_MEDIAN("Median", IAverage::calcMedian),
    AVERAGE_MID_RANGE("Mid range", IAverage::calcMidRange),
    AVERAGE_MODE("Mode", IAverage::calcMode);

    public final String option;
    final IAverageCalculate calculate;//Package private for a reason

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
}

package DOIFramework.core.extractors;

import DOIFramework.extractors.IReport;
import DOIFramework.statistics.IStatistics;
import DOIFramework.statistics.SimpleStatistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

/** A enum describing all possible Simple statistical implementations.
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public enum SimpleStatisticalReport implements IReport {
    POPULATION_VARIANCE("Population Variance", SimpleStatistics::calcPopulationVariance),

    SAMPLE_VARIANCE("Sample Variance", SimpleStatistics::calcSampleVariance),
    STANDARD_DEVIATION_POPULATION("Standard Deviation - From population", SimpleStatistics::calcStandardDeviationFromPopulation),
    STANDARD_DEVIATION_SAMPLE("Standard Deviation - From sample", SimpleStatistics::calcStandardDeviationFromSample);
    private static Class<? extends IStatistics> clazz = SimpleStatistics.class;

    public final String option;
    final IStatisticsCalculate calculate;

    @Contract(pure = true)
    SimpleStatisticalReport(String option, IStatisticsCalculate calculate) {
        this.option = option;
        this.calculate = calculate;
    }

    /**
     * @return String
     */
    @Contract(pure = true)
    @Override
    public String toString() { return option; }

    /**
     * @return {@link List}&lt;{@link SimpleStatisticalReport}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<SimpleStatisticalReport> getStandardConfiguration() {
        return Arrays.asList(STANDARD_DEVIATION_SAMPLE, SAMPLE_VARIANCE);
    }

    @Override
    public Class<? extends IStatistics> getMainClass() {
        return clazz;
    }
}

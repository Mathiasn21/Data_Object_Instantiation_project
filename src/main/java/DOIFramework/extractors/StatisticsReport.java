package DOIFramework.extractors;

import DOIFramework.statistics.IStatistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

/** A enum describing all possible Simple statistical implementations.
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public enum StatisticsReport {
    POPULATION_VARIANCE("Population Variance", IStatistics::calcPopulationVariance),
    SAMPLE_VARIANCE("Sample Variance", IStatistics::calcSampleVariance),
    STANDARD_DEVIATION_POPULATION("Standard Deviation - From population", IStatistics::calcStandardDeviationFromPopulation),
    STANDARD_DEVIATION_SAMPLE("Standard Deviation - From sample", IStatistics::calcStandardDeviationFromSample);

    public final String option;
    final IStatisticsCalculate calculate;

    @Contract(pure = true)
    StatisticsReport(String option, IStatisticsCalculate calculate) {
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
     * @return {@link List}&lt;{@link StatisticsReport}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<StatisticsReport> getStandardConfiguration() {
        return Arrays.asList(STANDARD_DEVIATION_SAMPLE, SAMPLE_VARIANCE);
    }
}

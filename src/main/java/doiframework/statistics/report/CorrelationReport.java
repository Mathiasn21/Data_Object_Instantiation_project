package doiframework.statistics.report;

import doiframework.statistics.calculations.*;
import doiframework.statistics.calculations.Correlation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public enum CorrelationReport implements IAdvancedReport {
    CORRELATION_SAMPLE("Correlation Coefficient - From sample", Correlation::calcCorrelationCoefficientFromSample),
    CORRELATION_POPULATION("Correlation Coefficient - From population", Correlation::calcCorrelationCoefficientFromPopulation);

    public final String option;
    public final ICorrelationCalculate calculate;
    private static final Class<? extends AdvancedStatistics> clazz = Correlation.class;

    @Contract(pure = true)
    CorrelationReport(String option, ICorrelationCalculate calculate) {
        this.option = option;
        this.calculate = calculate;
    }

    /**
     * @return {@link List}&lt;{@link CorrelationReport}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<CorrelationReport> getStandardConfiguration(){
        return Arrays.asList(CorrelationReport.values());
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
    public double calculate(Statistics advancedStatistics) throws Exception {
        double d = -1;
        if(advancedStatistics instanceof Correlation){
            d = calculate.execute((Correlation) advancedStatistics);
        }
        return d;
    }

    /**
     * @return String - option
     */
    @Contract(pure = true)
    @Override
    public String toString() { return option; }

    @Override
    public int getNumbSupportedDataSets() {
        return 2;
    }
}



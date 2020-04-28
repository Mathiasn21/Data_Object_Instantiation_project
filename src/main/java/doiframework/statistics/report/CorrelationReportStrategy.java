package doiframework.statistics.report;

import doiframework.statistics.calculations.*;
import doiframework.statistics.calculations.Correlation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

<<<<<<< HEAD:src/main/java/doiframework/statistics/report/CorrelationStrategy.java
enum CorrelationStrategy implements IAdvancedReport {
=======
public enum CorrelationReportStrategy implements IAdvancedReportContext {
>>>>>>> c93a378d9018681eadedf57f02946dee8e8c2df5:src/main/java/doiframework/statistics/report/CorrelationReportStrategy.java
    CORRELATION_SAMPLE("Correlation Coefficient - From sample", Correlation::calcCorrelationCoefficientFromSample),
    CORRELATION_POPULATION("Correlation Coefficient - From population", Correlation::calcCorrelationCoefficientFromPopulation);

    public final String option;
    public final ICorrelationCalculate calculate;
    private static final Class<? extends AdvancedStatistics> clazz = Correlation.class;

    @Contract(pure = true)
<<<<<<< HEAD:src/main/java/doiframework/statistics/report/CorrelationStrategy.java
    CorrelationStrategy(String option, ICorrelationCalculate calculate) {
=======
    CorrelationReportStrategy(String option, ICorrelationCalculate calculate) {
>>>>>>> c93a378d9018681eadedf57f02946dee8e8c2df5:src/main/java/doiframework/statistics/report/CorrelationReportStrategy.java
        this.option = option;
        this.calculate = calculate;
    }

<<<<<<< HEAD:src/main/java/doiframework/statistics/report/CorrelationStrategy.java
    /**
     * @return {@link List}&lt;{@link CorrelationStrategy}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<CorrelationStrategy> getStandardConfiguration(){
        return Arrays.asList(CorrelationStrategy.values());
    }

=======
>>>>>>> c93a378d9018681eadedf57f02946dee8e8c2df5:src/main/java/doiframework/statistics/report/CorrelationReportStrategy.java
    @Override
    public Class<? extends Statistics> getStatisticalClass() {
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



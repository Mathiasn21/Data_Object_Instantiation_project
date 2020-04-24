package doiframework.statistics.report;

import doiframework.statistics.calculations.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public enum CovarianceReport implements IAdvancedReport {
    COVARIANCE_SAMPLE("Covariance - From sample", Covariance::calcCovarianceFromSample),
    COVARIANCE_POPULATION("Covariance - From population", Covariance::calcCovarianceFromPopulation);

    public final String option;
    public final ICovarianceCalculate calculate;
    private static final Class<? extends AdvancedStatistics> clazz = Covariance.class;

    @Contract(pure = true)
    CovarianceReport(String option, ICovarianceCalculate calculate) {
        this.option = option;
        this.calculate = calculate;
    }

    /**
     * @return {@link List}&lt;{@link CovarianceReport}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<CovarianceReport> getStandardConfiguration(){
        return Arrays.asList(CovarianceReport.values());
    }

    @Override
    public Class<? extends Statistics> getMainClass() {
        return clazz;
    }

    @Override
    public String getOption() {
        return this.option;
    }

    //TODO: ask what this is lol
    @Override
    public double calculate(Statistics advancedStatistics) throws Exception {
        double d = -1;
        if(advancedStatistics instanceof Covariance){
            d = calculate.execute((Covariance) advancedStatistics);
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

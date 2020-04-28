package doiframework.statistics.report;

import doiframework.statistics.calculations.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

<<<<<<< HEAD:src/main/java/doiframework/statistics/report/CovarianceStrategy.java
enum CovarianceStrategy implements IAdvancedReport {
=======
public enum CovarianceReportStrategy implements IAdvancedReportContext {
>>>>>>> c93a378d9018681eadedf57f02946dee8e8c2df5:src/main/java/doiframework/statistics/report/CovarianceReportStrategy.java
    COVARIANCE_SAMPLE("Covariance - From sample", Covariance::calcCovarianceFromSample),
    COVARIANCE_POPULATION("Covariance - From population", Covariance::calcCovarianceFromPopulation);

    public final String option;
    public final ICovarianceCalculate calculate;
    private static final Class<? extends AdvancedStatistics> clazz = Covariance.class;

    @Contract(pure = true)
<<<<<<< HEAD:src/main/java/doiframework/statistics/report/CovarianceStrategy.java
    CovarianceStrategy(String option, ICovarianceCalculate calculate) {
=======
    CovarianceReportStrategy(String option, ICovarianceCalculate calculate) {
>>>>>>> c93a378d9018681eadedf57f02946dee8e8c2df5:src/main/java/doiframework/statistics/report/CovarianceReportStrategy.java
        this.option = option;
        this.calculate = calculate;
    }

<<<<<<< HEAD:src/main/java/doiframework/statistics/report/CovarianceStrategy.java
    /**
     * @return {@link List}&lt;{@link CovarianceStrategy}&gt;
     */
    @Contract(pure = true)
    @NotNull
    public static List<CovarianceStrategy> getStandardConfiguration(){
        return Arrays.asList(CovarianceStrategy.values());
    }

=======
>>>>>>> c93a378d9018681eadedf57f02946dee8e8c2df5:src/main/java/doiframework/statistics/report/CovarianceReportStrategy.java
    @Override
    public Class<? extends Statistics> getStatisticalClass() {
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

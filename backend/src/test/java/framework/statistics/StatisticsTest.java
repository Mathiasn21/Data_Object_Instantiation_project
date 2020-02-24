package framework.statistics;

import framework.statistics.Statistics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing statistics functions
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class StatisticsTest {
    double [] data = {1,2,3,4,5,6,6,6};
    double [] data2 = {2,5,6,7,7,8,9};

    Statistics statistics = new Statistics(data);
    Statistics statistics2 = new Statistics(data2);

    @Test
    public void sampleVarianceTest(){
        double sampleVariance = statistics.sampleVariance();
        assertEquals(3.8392857142857144, sampleVariance);
    }

    @Test
    public void populationVarianceTest(){
        double populationVariance = statistics.populationVariance();
        assertEquals(3.359375, populationVariance);
    }

    @Test
    public void standardDeviationFromSampleTest(){
        double STD = statistics.standardDeviationFromSample();
        assertEquals(1.9594095320493148, STD);
    }

    @Test
    public void standardDeviationFromPopulationTest(){
        double STD = statistics.standardDeviationFromPopulation();
        assertEquals(1.8328597873268975, STD);
    }

}

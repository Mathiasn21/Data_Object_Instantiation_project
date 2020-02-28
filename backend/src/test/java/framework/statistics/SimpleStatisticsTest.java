package framework.statistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing simpleStatistics functions
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class SimpleStatisticsTest {
    double [] data = {1,2,3,4,5,6,6,6};
    double [] data2 = {2,5,6,7,7,8,9};

    SimpleStatistics simpleStatistics = new SimpleStatistics(data);
    SimpleStatistics simpleStatistics2 = new SimpleStatistics(data2);

    @Test
    public void sampleVarianceTest(){
        double sampleVariance = simpleStatistics.sampleVariance();
        assertEquals(3.8392857142857144, sampleVariance);
    }

    @Test
    public void populationVarianceTest(){
        double populationVariance = simpleStatistics.populationVariance();
        assertEquals(3.359375, populationVariance);
    }

    @Test
    public void standardDeviationFromSampleTest(){
        double STD = simpleStatistics.standardDeviationFromSample();
        assertEquals(1.9594095320493148, STD);
    }

    @Test
    public void standardDeviationFromPopulationTest(){
        double STD = simpleStatistics.standardDeviationFromPopulation();
        assertEquals(1.8328597873268975, STD);
    }

}

package unitTests;

import DOIFramework.statistics.SimpleStatistics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing simpleStatistics functions
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class SimpleStatisticsTest {
    private final double [] data = {1,2,3,4,5,6,6,6};

    private final SimpleStatistics simpleStatistics = new SimpleStatistics(data);

    @Test
    public void sampleVarianceTest(){
        double sampleVariance = simpleStatistics.calcSampleVariance();
        assertEquals(3.8392857142857144, sampleVariance);
    }

    @Test
    public void populationVarianceTest(){
        double populationVariance = simpleStatistics.calcPopulationVariance();
        assertEquals(3.359375, populationVariance);
    }

    @Test
    public void standardDeviationFromSampleTest(){
        double STD = simpleStatistics.calcStandardDeviationFromSample();
        assertEquals(1.9594095320493148, STD);
    }

    @Test
    public void standardDeviationFromPopulationTest(){
        double STD = simpleStatistics.calcStandardDeviationFromPopulation();
        assertEquals(1.8328597873268975, STD);
    }

    @Test
    public void standardErrorFromSampleTest(){
        double STE = simpleStatistics.calcStandardErrorFromSample();
        assertEquals(0.6927558836168151, STE);
    }

    @Test
    public void standardErrorFromPopulationTest(){
        double STE = simpleStatistics.calcStandardErrorFromPopulation();
        assertEquals(0.648, STE);
    }

}

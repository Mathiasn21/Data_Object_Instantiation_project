package framework.statistics;

import framework.statistics.Statistics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void averageMeanTest(){
        double averageMean = statistics.averageMean();
        assertTrue(4.125 == averageMean);
        assertFalse(6 == averageMean);
    }

    @Test
    public void calcMedianTest(){
        double medianEven = statistics.calcMedian();
        double medianOdd = statistics2.calcMedian();

        System.out.println(medianEven);
        System.out.println(medianOdd);
        assertTrue(4.5 == medianEven);
        assertTrue(7 == medianOdd);
    }

    @Test
    public void averageModeTest(){
        double averageMode = statistics.averageMode();
        double averageMode2 = statistics2.averageMode();

        assertTrue(6 == averageMode);
        assertTrue(7 == averageMode2);
    }

    @Test
    public void midRangeTest(){
        double midRange = statistics.averageMidRange();
        assertTrue(3.5 == midRange);
    }

    @Test
    public void sampleVarianceTest(){
        double sampleVariance = statistics.sampleVariance();
        assertTrue(3.8392857142857144 == sampleVariance);
    }

    @Test
    public void populationVarianceTest(){
        double populationVariance = statistics.populationVariance();
        assertTrue(3.359375 == populationVariance);
    }

    @Test
    public void standardDeviationFromSampleTest(){
        double STD = statistics.standardDeviationFromSample();
        assertTrue(1.9594095320493148 == STD);
    }

    @Test
    public void standardDeviationFromPopulationTest(){
        double STD = statistics.standardDeviationFromPopulation();
        assertTrue(1.8328597873268975 == STD);
    }

}

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
    public void calcSum(){
        double sum = statistics.calcSum();
        assertEquals(33.0, sum);
    }

    @Test
    public void averageMeanTest(){
        double averageMean = statistics.averageMean();
        System.out.println(averageMean);
        assertEquals(4.125, averageMean);
        assertNotEquals(6, averageMean, 0.0);
    }

    @Test
    public void calcMedianTest(){
        double medianEven = statistics.calcMedian();
        double medianOdd = statistics2.calcMedian();

        System.out.println(medianEven);
        System.out.println(medianOdd);
        assertEquals(4.5, medianEven);
        assertEquals(7, medianOdd);
    }

    @Test
    public void averageModeTest(){
        double averageMode = statistics.averageMode();
        double averageMode2 = statistics2.averageMode();

        assertEquals(6, averageMode);
        assertEquals(7, averageMode2);
    }

    @Test
    public void midRangeTest(){
        double midRange = statistics.averageMidRange();
        assertEquals(3.5, midRange);
    }

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

import framework.statistics.Statistics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatisticsTest {
    double [] data = {1,2,3,4,5,6,6,6};
    double [] data2 = {2,5,6,7,7,8,9};

    Statistics statistics = new Statistics(data);
    Statistics statistics2 = new Statistics(data2);

    @Test
    public void calcAverageMeanTest(){
        double averageMean = statistics.calcAverageMean();
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
    public void calcAverageMode(){
        double averageMode = statistics.calcAverageMode();
        double averageMode2 = statistics2.calcAverageMode();

        assertTrue(6 == averageMode);
        assertTrue(7 == averageMode2);
    }

    @Test
    public void calcMidRange(){
        double midRange = statistics.calcAverageMidRange();
        assertTrue(3.5 == midRange);
    }

    @Test
    public void calcSampleVariance(){
        double sampleVariance = statistics.calcSampleVariance();
        System.out.println(sampleVariance);
        assertTrue(3.83928571429 == sampleVariance);
    }

}

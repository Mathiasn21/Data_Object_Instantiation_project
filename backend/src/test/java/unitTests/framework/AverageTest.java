package unitTests.framework;

import framework.statistics.Average;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Class for testing average functions
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class AverageTest {
    private final double [] data = {1,2,3,4,5,6,6,6};
    private final double [] data2 = {2,5,6,7,7,8,9};

    private final Average average = new Average(data);
    private final Average average2 = new Average(data2);

    @Test
    public void calcSum(){
        double sum = average.calcSum();
        assertEquals(33.0, sum);
    }
    @Test
    public void averageMeanTest(){
        double averageMean = average.calcMean();
        System.out.println(averageMean);
        assertEquals(4.125, averageMean);
        assertNotEquals(6, averageMean, 0.0);
    }

    @Test
    public void calcMedianTest(){
        double medianEven = average.calcMedian();
        double medianOdd = average2.calcMedian();

        System.out.println(medianEven);
        System.out.println(medianOdd);
        assertEquals(4.5, medianEven);
        assertEquals(7, medianOdd);
    }

    @Test
    public void averageModeTest(){
        double averageMode = average.calcMode();
        double averageMode2 = average2.calcMode();

        assertEquals(6, averageMode);
        assertEquals(7, averageMode2);
    }

    @Test
    public void midRangeTest(){
        double midRange = average.calcMidRange();
        assertEquals(3.5, midRange);
    }

}

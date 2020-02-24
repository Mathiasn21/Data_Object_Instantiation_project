package framework.statistics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Class for testing average functions
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public class AverageTest {
    double [] data = {1,2,3,4,5,6,6,6};
    double [] data2 = {2,5,6,7,7,8,9};

    Average average = new Average(data);
    Average average2 = new Average(data2);

    @Test
    public void calcSum(){
        double sum = average.calcSum();
        assertEquals(33.0, sum);
    }
    @Test
    public void averageMeanTest(){
        double averageMean = average.averageMean();
        System.out.println(averageMean);
        assertEquals(4.125, averageMean);
        assertNotEquals(6, averageMean, 0.0);
    }

    @Test
    public void calcMedianTest(){
        double medianEven = average.median();
        double medianOdd = average2.median();

        System.out.println(medianEven);
        System.out.println(medianOdd);
        assertEquals(4.5, medianEven);
        assertEquals(7, medianOdd);
    }

    @Test
    public void averageModeTest(){
        double averageMode = average.averageMode();
        double averageMode2 = average2.averageMode();

        assertEquals(6, averageMode);
        assertEquals(7, averageMode2);
    }

    @Test
    public void midRangeTest(){
        double midRange = average.averageMidRange();
        assertEquals(3.5, midRange);
    }

}
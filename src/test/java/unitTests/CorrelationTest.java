package unitTests;

import DOIFramework.statistics.Correlation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CorrelationTest {
    private final double [] data1 = {1,2,3,4,5,6,6,6};
    private final double [] data2 = {2,5,6,7,7,8,9,6};

    Correlation corr = new Correlation(data1, data2);

    @Test
    public void CorrelationCoefficientFromSampleTest(){
        assertDoesNotThrow(() -> {
            double r = corr.calcCorrelationCoefficientFromSample();
            assertEquals(1, r);
        });

    }
    @Test
    public void CorrelationCoefficientFromPopulationTest(){
        assertDoesNotThrow(() -> {
            double r = corr.calcCorrelationCoefficientFromPopulation();
            assertEquals(1, r);
        });
    }
}

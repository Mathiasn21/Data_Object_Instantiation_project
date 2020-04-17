package unitTests;

import doiframework.statistics.calculations.Covariance;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CovarianceTest {
    private final double [] data = {1,2,3,4,5,6,6,6};
    private final double [] data2 = {2,5,6,7,7,8,9,6};

    Covariance cov = new Covariance(data, data2);

    @Test
    public void covarianceFromSampleTest(){
        assertDoesNotThrow(() -> {
            double covFromSample = cov.calcCovarianceFromSample();
            assertEquals(3.5357142857143, covFromSample);
        });
    }

    @Test
    public void covarianceFromPopulationTest(){
        assertDoesNotThrow(() -> {
            double covFromPopulation = cov.calcCovarianceFromPopulation();
            assertEquals(3.09375, covFromPopulation);
        });
    }

}

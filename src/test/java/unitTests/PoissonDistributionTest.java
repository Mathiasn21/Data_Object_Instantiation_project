package unitTests;

import doiframework.statistics.calculations.PoissonDistribution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PoissonDistributionTest {
    PoissonDistribution p = new PoissonDistribution();
    double x;

    @Test
    public void calcPoissonExcpectedValueTest(){
        x = p.calcExcpectedValue(3, 2.5);
        assertEquals(7.5, x);
    }
    @Test
    public void calcPoissonVarianceTest(){
        x = p.calcVariance(3, 2.5);
        assertEquals(7.5, x);
    }
    @Test
    public void calcPossionDistributionTest(){
        x = p.calcPoissonDistribution(10, 7.5);
        assertEquals(0.08583037040867353, x);
    }
    @Test
    public void calcCumulativeProbabilityLessThanEqualTest(){
        x = p.calcCumulativeProbabilityLessThanEqual(10, 7.5);
        assertEquals(0.8622379834283879, x);
        System.out.println(p.calcCumulativeProbabilityMoreThan(10, 7.5));
    }

    @Test
    public void calcCumulativeProbabilityMoreThanTest(){
        x = p.calcCumulativeProbabilityMoreThan(10, 7.5);
        assertEquals(0.13776201657161213, x);
    }

    @Test
    public void calcCumulativeProbabilityMoreThanEqualTest(){
        x = p.calcCumulativeProbabilityMoreThanEqual(10, 7.5);
        assertEquals(0.22359238698028572, x);
    }
}

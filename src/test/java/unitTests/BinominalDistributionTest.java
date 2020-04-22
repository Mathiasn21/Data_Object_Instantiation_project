package unitTests;

import doiframework.statistics.calculations.BinominalDistribution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinominalDistributionTest {
    BinominalDistribution b = new BinominalDistribution(6, 0.2);
    double p;

    @Test
    public void binominalProbabilityTest(){
        p = b.calcBinominalProbability(2);
        assertEquals(0.2457600000000001, p);
    }

    @Test
    public void binominalExcpectedValueTest(){
        p = b.calcBinominalExcpectedValue();
        assertEquals(1.2000000000000002, p);
    }

    @Test
    public void binominalVarianceTest(){
        p = b.calcBinominalVariance();
        assertEquals(0.9600000000000002, p);
    }
}

package unitTests;

import doiframework.statistics.calculations.BinominalDistribution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinominalDistributionTest {
    BinominalDistribution b = new BinominalDistribution(6, 0.2);
    BinominalDistribution b2 = new BinominalDistribution(25, 0.4);

    double p;
    double p2;

    @Test
    public void binominalProbabilityTest(){
        p = b.calcBinominalProbability(2);
        p2 = b2.calcBinominalProbability(14);
        assertEquals(0.2457600000000001, p);
        assertEquals(0.043409545776394304, p2);
    }

    @Test
    public void binominalExcpectedValueTest(){
        p = b.calcExcpectedValue();
        assertEquals(1.2000000000000002, p);
    }

    @Test
    public void binominalVarianceTest(){
        p = b.calcVariance();
        assertEquals(0.9600000000000002, p);
    }

    @Test
    public void binominalCumulativeLessThanEqualTest(){
        p = b.calCumulativeProbabilityLessThanEqual(2);
        p2 = b2.calCumulativeProbabilityLessThanEqual(14);
        assertEquals(0.9656084819093212, p2);
        assertEquals(0.9011200000000004, p);
    }

    @Test
    public void binominalCumulativeMoreThanTest(){
        p = b.calCumulativeProbabilityMoreThan(2);
        p2 = b2.calCumulativeProbabilityMoreThan(14);
        assertEquals(0.09887999999999963, p);
        assertEquals(0.03439151809067875, p2);
    }

    @Test
    public void binominalCumulativeMoreThanEqualTest(){
        p2 = b2.calCumulativeProbabilityMoreThanEqual(14);
        assertEquals(0.07780106386707308, p2);
    }
}

package unitTests;

import doiframework.statistics.calculations.TTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TTestTest {
    private final double [] data1 = {0.27, 0.25, 0.26, 0.28, 0.29};
    private final double [] data2 = {0.24, 0.26, 0.23, 0.25, 0.24, 0.22};

    TTest t = new TTest(data1, data2);

    @Test
    public void TTestUnpairedTest(){
        double unpaired = t.unpaired();
        assertEquals(0, 1);
    }

}

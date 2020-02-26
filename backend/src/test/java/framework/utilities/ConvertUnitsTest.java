package framework.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ConvertUnitsTest {

    @Test
    public void tousandthTest(){
        assertEquals(0.322,ConvertUnits.convertToThousandth());
    }
}

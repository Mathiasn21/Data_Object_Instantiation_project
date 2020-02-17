package framework.display;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Chart tests: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChartTest {
    Chart chart = new Chart();

    @Test
    @Order(0)
    void nullTest(){
        assertNull(chart.typeOfChart());
    }
}

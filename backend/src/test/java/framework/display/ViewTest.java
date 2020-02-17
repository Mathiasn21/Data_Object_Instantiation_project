package framework.display;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("View tests: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViewTest {
    View view = new View();

    @Test
    @Order(0)
    void nullTest(){
        assertNull(view.getData());
    }
}

package framework.API;

import API.MainHandler;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MainHandler tests: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViewTest {
    MainHandler mainHandler = new MainHandler();

    @Test
    @Order(0)
    void nullTest(){
        //assertNull(mainHandler.getData());
    }
}

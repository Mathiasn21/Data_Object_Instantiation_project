package unitTests.framework;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

public class HandleData {
    @Test
    void handle_json() throws IOException {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;
        Resource resource = Resource.getBuilder().fromFile(path).build();

        JSONHandler jsonHandler = new JSONHandler();
        List<Object[]> list = jsonHandler.handle(resource.getData());
    }
}

package unitTests.framework;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class HandleData {
    @Test
    void handle_json() throws IOException {
        String path = System.getProperty("user.dir") + "/files/testingJSONFile.json" ;
        Resource resource = Resource.getBuilder().fromFile(path).build();

        IHandle jsonHandler = new JSONHandler();
        System.out.println(jsonHandler.handle(resource.getData()));
    }
}

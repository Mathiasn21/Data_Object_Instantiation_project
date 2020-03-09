package unitTests.framework;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class HandleData {
    @Test
    void handle_json() throws IOException {
        Resource resource = Resource.getBuilder().fromFile("/files/testingJSONFile.json").build();

        IHandle jsonHandler = new JSONHandler();
        jsonHandler.handle(resource.getData());


    }
}

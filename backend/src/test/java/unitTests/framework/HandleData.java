package unitTests.framework;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

public class HandleData {
    @Test
    void handle_json_with_only_primitives() throws IOException {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;
        Resource resource = Resource.newResource().fromFile(path).build();

        JSONHandler jsonHandler = new JSONHandler();
        List<Object[]> list = jsonHandler.handle(resource.getData());
    }


    @Test
    void handle_json_with_primitive_arrays(){
        //TODO: implement test cases for this
        Assertions.fail();
    }

    @Test
    void handle_csv_with_only_primitives(){
        //TODO: implement test cases for this
        Assertions.fail();
    }

    @Test
    void handle_csv_with_primitive_arrays(){
        //TODO: implement test cases for this
        Assertions.fail();
    }
}
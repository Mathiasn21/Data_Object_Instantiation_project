package unitTests;

import framework.data.Resource;
import framework.utilities.handlers.JSONHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

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
        fail();
    }

    @Test
    void handle_csv_with_only_primitives(){
        //TODO: implement test cases for this
        fail();
    }

    @Test
    void handle_csv_with_primitive_arrays(){
        //TODO: implement test cases for this
        fail();
    }
}

package unitTests;

import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.JSONHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HandleData {
    @Test
    void handle_json_with_only_primitives() throws IOException {
        Class<?>[] classes = {String.class, String.class, String.class, Integer.class};
        String path = System.getProperty("user.dir") + "/files/jsonNoArrays.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();

        JSONHandler jsonHandler = new JSONHandler();
        List<Object[]> list = jsonHandler.handle(dataSource.getData());
        assertFalse(list.isEmpty());

        Object[] sample = list.get(0);
        for(int i = 0; i < sample.length; i++){
            assertSame(sample[i].getClass(), classes[i]);
        }
    }


    @Test
    void handle_json_with_primitive_arrays() throws IOException {
        Class<?>[] classes = {String.class, String.class, String.class, int[].class};
        String path = System.getProperty("user.dir") + "/files/testingJSONFile.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();

        JSONHandler jsonHandler = new JSONHandler();
        List<Object[]> list = jsonHandler.handle(dataSource.getData());
        assertFalse(list.isEmpty());

        Object[] sample = list.get(0);
        for(int i = 0; i < sample.length; i++){
            assertSame(sample[i].getClass(), classes[i]);
        }
    }

    @Test
    void handle_json_with_primitive_arrays_n_dimensions() throws IOException {
        Class<?>[] classes = {String.class, String.class, String.class, int[][][].class};
        String path = System.getProperty("user.dir") + "/files/3Darrays.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();

        JSONHandler jsonHandler = new JSONHandler();
        List<Object[]> list = jsonHandler.handle(dataSource.getData());
        assertFalse(list.isEmpty());

        Object[] sample = list.get(0);
        for(int i = 0; i < sample.length; i++){
            assertSame(sample[i].getClass(), classes[i]);
        }
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

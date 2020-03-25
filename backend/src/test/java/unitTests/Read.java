package unitTests;

import framework.utilities.data.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.net.URL;

public class Read {
    @Test
    void from_file_using_string() {
        Assertions.assertDoesNotThrow(() -> {
            String path = System.getProperty("user.dir") + "/files/DTOJson.json";
            Resource resource = Resource.newResource().fromFile(path).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            Assertions.assertFalse(builder.toString().isEmpty());
            Assertions.assertFalse(builder.toString().isBlank());
        });
    }

    @Test
    void from_file_using_file() {
        Assertions.assertDoesNotThrow(() -> {
            String path = System.getProperty("user.dir") + "/files/DTOJson.json";
            Resource resource = Resource.newResource().fromFile(path).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            Assertions.assertFalse(builder.toString().isEmpty());
            Assertions.assertFalse(builder.toString().isBlank());
        });
    }

    @Test
    void from_URL_using_URL() {
        Assertions.assertDoesNotThrow(() -> {
            URL url = new URL("http://example.com");
            Resource resource = Resource.newResource().fromURL(url).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            Assertions.assertFalse(builder.toString().isEmpty());
            Assertions.assertFalse(builder.toString().isBlank());
        });
    }
    @Test
    void from_URL_using_string() {
        //TODO: implement test

        Assertions.assertDoesNotThrow(() -> {
            String URL = "http://example.com";
            Resource resource = Resource.newResource().fromURL(URL).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            Assertions.assertFalse(builder.toString().isEmpty());
            Assertions.assertFalse(builder.toString().isBlank());
        });
    }
}

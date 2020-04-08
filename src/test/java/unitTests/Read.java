package unitTests;

import DOIFramework.core.resource.Resource;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class Read {
    @Test
    void from_file_using_file() {
        assertDoesNotThrow(() -> {
            File path = new File(System.getProperty("user.dir") + "/files/DTOJson.json");
            Resource resource = Resource.newResource().fromFile(path).build();

            BufferedReader bufferedReader = resource.getData();
            StringBuilder builder = new StringBuilder();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            assertFalse(builder.toString().isEmpty());
            assertFalse(builder.toString().isBlank());
        });
    }

    @Test
    void from_URL_using_URL() {
        assertDoesNotThrow(() -> {
            URL url = new URL("http://example.com");
            Resource resource = Resource.newResource().fromURL(url).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            assertFalse(builder.toString().isEmpty());
            assertFalse(builder.toString().isBlank());
        });
    }

    @Test
    void from_URL_using_string() {
        assertDoesNotThrow(() -> {
            String URL = "http://example.com";
            Resource resource = Resource.newResource().fromURL(URL).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            assertFalse(builder.toString().isEmpty());
            assertFalse(builder.toString().isBlank());
        });
    }
}

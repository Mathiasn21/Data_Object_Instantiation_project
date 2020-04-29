package unitTests;

import doiframework.core.resource.DataSource;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class Read {
    @Test
    void from_file_using_file() {
        assertDoesNotThrow(() -> {
            File path = new File(System.getProperty("user.dir") + "/files/showcaseAPI.json");
            DataSource dataSource = DataSource.newResource().fromFile(path).build();

            BufferedReader bufferedReader = dataSource.getData();
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
            DataSource dataSource = DataSource.newResource().fromURL(url).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = dataSource.getData();

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
            DataSource dataSource = DataSource.newResource().fromURL(URL).build();

            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = dataSource.getData();

            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            assertFalse(builder.toString().isEmpty());
            assertFalse(builder.toString().isBlank());
        });
    }
}

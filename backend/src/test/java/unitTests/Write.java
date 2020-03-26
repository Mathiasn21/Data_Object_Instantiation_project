package unitTests;

import framework.utilities.data.Resource;
import framework.utilities.data.write.WriteFile;
import framework.utilities.data.write.WriteURL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.fail;

public class Write {

    @Test
    void to_file_using_string(){
    Assertions.assertDoesNotThrow(() -> {
        String path;
        path = System.getProperty("user.dir") + "/files/writeToTest.txt";
        WriteFile write = WriteFile.getObj();
        write.given(path, "StringPath;");

        Resource resource = Resource.newResource().fromFile(path).build();
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = resource.getData();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        Assertions.assertTrue(builder.toString().contains("StringPath"));
        Assertions.assertTrue(builder.toString().contains("StringPath"));
    });
    }

    @Test
    void to_file_using_path() {
        Assertions.assertDoesNotThrow(() -> {
            WriteFile write = WriteFile.getObj();
            File path = new File(System.getProperty("user.dir") + "/files/writeToTest.txt");
            write.given(path, "FilePath;");

            Resource resource = Resource.newResource().fromFile(path).build();
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            Assertions.assertTrue(builder.toString().contains("FilePath"));
            Assertions.assertTrue(builder.toString().contains("FilePath"));
        });
    }

    @Test
    void to_URL_using_string()  {
        //TODO: make sure this is the right way to test
        Assertions.assertDoesNotThrow(() -> {
            String url = "http://example.com";
            WriteURL write = WriteURL.getObj();
            write.given(url, "writing...");

            URL url_status = new URL(url);
            HttpURLConnection http = (HttpURLConnection)url_status.openConnection();
            Assertions.assertEquals(200, http.getResponseCode());
        });
    }

    @Test
    void to_URL_using_URL()  {
        //TODO: make sure this is the right way to test

        Assertions.assertDoesNotThrow(() -> {
            URL url = new URL("http://example.com");

            WriteURL write = WriteURL.getObj();
            write.given(url, "writing...");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            Assertions.assertEquals(200, http.getResponseCode());
        });
    }

    @Test
    void to_URL_using_URL_appending(){
        //TODO: implement method
        fail();
    }
    @Test
    void to_URL_using_string_appending(){
        //TODO: implement method
        fail();
    }
    @Test
    void to_file_using_file_appending(){
        //TODO: implement method
        fail();
    }
    @Test
    void to_file_using_string_appending(){

        //FIXME: doesn't work
        Assertions.assertDoesNotThrow(() -> {
            String path;
            path = System.getProperty("user.dir") + "/files/writeToTest.txt";
            WriteFile write = WriteFile.getObj();
            write.appendDataGiven(path, "wwappending2");



            //Reads from resource
            Resource resource = Resource.newResource().fromFile(path).build();
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            Assertions.assertTrue(builder.toString().contains("appending"));
            Assertions.assertTrue(builder.toString().contains("appending"));
        });
    }

    @Test
    void create_file()  {
        Assertions.assertDoesNotThrow(() -> {
            File path = new File(System.getProperty("user.dir") + "/files/createFile.csv");
            WriteFile w = WriteFile.getObj();
            w.createFile(path);
            Assertions.assertTrue(path.exists());
        });
    }

    @Test
    void delete_file()  {
        Assertions.assertDoesNotThrow(() -> {
            File path = new File(System.getProperty("user.dir") + "/files/createFile.csv");
            WriteFile w = WriteFile.getObj();
            w.deleteFile(path);
            Assertions.assertFalse(path.exists());
        });
    }



}


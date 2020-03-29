package unitTests;

import framework.utilities.data.IWriteCommand;
import framework.utilities.data.Resource;
import framework.utilities.data.write.WriteFileWriteCommand;
import framework.utilities.data.write.WriteURLWriteCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class Write {

    @Test
    void to_file_using_string(){
    Assertions.assertDoesNotThrow(() -> {
        String path = System.getProperty("user.dir") + "/files/writeToTest.txt";
        IWriteCommand writer = new WriteFileWriteCommand(path, "StringPath;");
        writer.execute();

        Resource resource = Resource.newResource().fromFile(path).build();
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = resource.getData();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        assertTrue(builder.toString().contains("StringPath"));
    });
    }

    @Test
    void to_file_using_path() {
        Assertions.assertDoesNotThrow(() -> {
            String path = System.getProperty("user.dir") + "/files/writeToTest.txt";
            WriteFileWriteCommand writer = new WriteFileWriteCommand(path, "StringPath;");
            writer.execute();

            Resource resource = Resource.newResource().fromFile(path).build();
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            assertTrue(builder.toString().contains("StringPath;"));
        });
    }

    @Test
    void to_URL_using_string()  {
        //TODO: make sure this is the right way to test
        Assertions.assertDoesNotThrow(() -> {
            String url = "";//Pick another URL - or use a stub for testing https
            WriteURLWriteCommand write = new WriteURLWriteCommand(url, "writing...");
            write.execute();

            URL url_status = new URL(url);
            HttpURLConnection http = (HttpURLConnection)url_status.openConnection();
            assertEquals(200, http.getResponseCode());
        });
    }

    @Test
    void to_URL_using_URL()  {
        //TODO: make sure this is the right way to test

        Assertions.assertDoesNotThrow(() -> {
            URL url = new URL("http://example.com");

            WriteURLWriteCommand write = new WriteURLWriteCommand(url, "writing...");
            write.execute();

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            assertEquals(200, http.getResponseCode());
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
        Assertions.assertDoesNotThrow(() -> {
            String path;
            path = System.getProperty("user.dir") + "/files/writeToTest.txt";
            WriteFileWriteCommand write = new WriteFileWriteCommand(path, "fappening");
            write.execute();

            //Reads from resource
            Resource resource = Resource.newResource().fromFile(path).build();
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            assertTrue(builder.toString().contains("fappening"));
            assertTrue(builder.toString().contains("fappening"));
        });
    }

    @Test
    void create_file()  {
        Assertions.assertDoesNotThrow(() -> {
            File path = new File(System.getProperty("user.dir") + "/files/createFile.csv");
            WriteFileWriteCommand w = new WriteFileWriteCommand(path, "thing");
            w.createFile(path);
            assertTrue(path.exists());
        });
    }

    @Test
    void delete_file()  {
        Assertions.assertDoesNotThrow(() -> {
            File path = new File(System.getProperty("user.dir") + "/files/createFile.csv");
            WriteFileWriteCommand w = new WriteFileWriteCommand(path, "thing");
            w.remove(path);
            Assertions.assertFalse(path.exists());
        });
    }



}


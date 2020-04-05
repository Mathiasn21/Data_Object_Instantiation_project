package unitTests;

import framework.data.commands.write.IWriteCommand;
import framework.data.Resource;
import framework.data.commands.write.WriteFileCommand;
import framework.data.commands.write.WriteURLCommand;
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
            IWriteCommand writer = new WriteFileCommand(path);
            writer.execute( "StringPath;");

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
            WriteFileCommand writer = new WriteFileCommand(path);
            writer.execute( "StringPath;");

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
            WriteURLCommand write = new WriteURLCommand(url);
            write.execute("writing...");

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

            WriteURLCommand write = new WriteURLCommand(url);
            write.execute("writing...");

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
            WriteFileCommand write = new WriteFileCommand(path);
            write.execute("fappening");

            //Reads from resource
            Resource resource = Resource.newResource().fromFile(path).build();
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = resource.getData();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            assertTrue(builder.toString().contains("fappening"));
        });
    }

    @Deprecated
    @Test
    void delete_file()  {
        Assertions.assertDoesNotThrow(() -> {
            File path = new File(System.getProperty("user.dir") + "/files/createFile.csv");
            Assertions.assertFalse(path.exists());
        });
    }
}


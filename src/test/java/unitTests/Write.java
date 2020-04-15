package unitTests;

import DOIFramework.core.resource.commands.write.IWriteCommand;
import DOIFramework.core.resource.Resource;
import DOIFramework.core.resource.commands.write.FileCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

public class Write {

    @Test
    void to_file_using_string(){
        Assertions.assertDoesNotThrow(() -> {
            String path = System.getProperty("user.dir") + "/files/writeToTest.txt";
            IWriteCommand writer = new FileCommand(path);
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
            FileCommand writer = new FileCommand(path);
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
    void to_file_using_by_appending(){
        Assertions.assertDoesNotThrow(() -> {
            String path;
            path = System.getProperty("user.dir") + "/files/writeToTest.txt";
            FileCommand write = new FileCommand(path);
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

    //TODO: Implement these methods @Maria
    @Test
    void to_URL_using_string()  {
        //TODO: make sure this is the right way to test - eg: execute POST request
        fail();
    }

    @Test
    void to_URL_using_URL() {
        //TODO: Implement test and make sure you utilize mocks for this
        fail();
    }
}


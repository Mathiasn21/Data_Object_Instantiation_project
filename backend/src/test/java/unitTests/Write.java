package unitTests;

import framework.utilities.data.Resource;
import framework.utilities.data.write.WriteFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class Write {

    @Test
    void to_file_using_string(){
    Assertions.assertDoesNotThrow(() -> {
        String path;
        path = System.getProperty("user.dir") + "/files/writeToTest.csv";
        WriteFile write = new WriteFile();
        write.given(path, "StringPath");

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
            File path = new File(System.getProperty("user.dir") + "/files/writeToTest.csv");
            WriteFile write = new WriteFile();
            write.given(path, "FilePath");

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
        System.out.println("hey");
    }
    @Test
    void to_URL_using_file()  {
        System.out.println("hey");
    }

}


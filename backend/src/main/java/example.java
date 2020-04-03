import framework.utilities.data.Parser;
import framework.utilities.data.Resource;
import framework.utilities.data.write.IWriteCommand;
import framework.utilities.data.write.WriteFileWriteCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class example {

    //Shows how to read from a file
    public static StringBuilder from_file_using_file() throws IOException {
        File path = new File(System.getProperty("user.dir") + "/files/DTOJson.json");
        Resource resource = Resource.newResource().fromFile(path).build();
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = resource.getData();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        return builder;
    }

    //Shows how to write to a file
    public static StringBuilder to_file_using_string() throws IOException {
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
        return builder;
    }

    public static Object from_string_to_primitive_value(){
        String value = "22.34";
        Parser.classToValueFromObject(value.getClass(), value);
        return value;
    }

    public static boolean is_primitve_class() {
        String s = "Hei";
        return Parser.isPrimitiveType(s.getClass());
    }
}

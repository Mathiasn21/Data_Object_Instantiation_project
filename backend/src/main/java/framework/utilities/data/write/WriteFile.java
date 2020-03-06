package framework.utilities.data.write;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

//TODO: implement remaining logic from another project code -> Mathias
public final class WriteFile {
    /**
     * Standard method for writing data to a given file.
     * This method does not append but overwrites!
     * Utilizes a relative path for top level directory, plus filename.extension
     * @param str   String
     * @param fName String
     */
    private static void writeToFile(String str, String fName) {
        String filepath = "/files/" + fName;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("").getAbsolutePath() + filepath))) {
            bufferedWriter.write(str);
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    /**
     * Utilizes parametrization combined with generics, in order to
     * convert a given T[] object and its specified Class template to json format.
     *
     * @param list T[]
     * @return String
     */
    private static String toJson(List<String> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list);
    }
}

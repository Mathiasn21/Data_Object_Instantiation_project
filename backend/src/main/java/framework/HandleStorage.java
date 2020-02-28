package framework;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class HandleStorage {

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
     * Returns a buffer for reading the given file.
     * Charset is standard UTF-8
     * @param fName String
     * @return BufferedReader BufferedReader {@link BufferedReader}
     * @throws IOException IOException {@link IOException}
     */
    @NotNull
    public static BufferedReader readFromFile(@NotNull String fName) throws IOException {
        String filepath = "/files/" + fName;
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File("").getAbsolutePath() + filepath), StandardCharsets.UTF_8));
    }



    /**
     * This method retrieves a list, given a type Class and a json string.
     *
     * @param type             T[]
     * @param jsonTextFromFile String
     * @param <T>              T
     * @return {@link List}
     */
    @NotNull
    @SuppressWarnings("unchecked")//Will always be possible otherwise and exception is thrown waaay before this method
    private static <T> List<T> listFromJson(Class<T> type, String jsonTextFromFile) {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        Class<T[]> arrClass = (Class<T[]>) Array.newInstance(type, 0).getClass();
        T[] arrangementArray = gson.fromJson(jsonTextFromFile, arrClass);
        return (Arrays.asList(arrangementArray));
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

    public static JsonArray readFromJSONFile(String filename) throws IOException {
        BufferedReader bufferedReader = readFromFile(filename);
        StringBuilder textFromFile = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            textFromFile.append(line);
        }

        JsonElement jsonObject = JsonParser.parseString(textFromFile.toString());
        return jsonObject.getAsJsonArray();
    }
}

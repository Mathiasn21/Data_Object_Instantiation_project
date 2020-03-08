package framework.utilities.data.read;

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
public final class ReadFile implements IReadFile{
    private String path;
    /**
     * Returns a buffer for reading the given file.
     * Charset is standard UTF-8
     * @param fileName String
     * @return BufferedReader BufferedReader {@link BufferedReader}
     * @throws IOException IOException {@link IOException}
     */
    public void given(@NotNull String fileName) throws IOException {
        path = "/files/" + fileName;
    }

    @Override
    public BufferedReader read() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File("").getAbsolutePath() + path), StandardCharsets.UTF_8));
    }

    @Override
    public @NotNull ReadFile given(@NotNull File resource) throws IOException {
        //TODO: Implement logic
        return null;
    }


    public JsonArray fromJSON() throws IOException {
        //TODO: Alter logic in order to divide json text in another class

        BufferedReader bufferedReader = read();
        StringBuilder textFromFile = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            textFromFile.append(line);
        }

        JsonElement jsonObject = JsonParser.parseString(textFromFile.toString());
        return jsonObject.getAsJsonArray();
    }


    /**
     * This method retrieves a list, given a type Class and a json string.
     * @param type             T[]
     * @param jsonTextFromFile String
     * @param <T>              T
     * @return {@link List}
     */
    @NotNull
    @SuppressWarnings("unchecked")//Will always be possible otherwise and exception is thrown waaay before this method
    private <T> List<T> listFromJson(Class<T> type, String jsonTextFromFile) {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        Class<T[]> arrClass = (Class<T[]>) Array.newInstance(type, 0).getClass();
        T[] arrangementArray = gson.fromJson(jsonTextFromFile, arrClass);
        return (Arrays.asList(arrangementArray));
    }
}

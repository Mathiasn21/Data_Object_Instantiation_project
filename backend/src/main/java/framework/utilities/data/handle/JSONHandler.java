package framework.utilities.data.handle;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/** Class that contains concrete instructions for handling JSON data.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class JSONHandler implements IHandle{
    private Class<?>[] primaryKeyTypes;
    private String[] primaryKeys;

    /**
     * @param types {@link Class}&lt;?&gt;[]
     */
    @Override
    public final void setPrimaryKeyTypes(@NotNull Class<?>[] types) {
        primaryKeyTypes = types;
    }

    /**
     * @param keys String[]
     */
    @Override
    public final void setPrimaryKeys(@NotNull String[] keys) {
        primaryKeys = keys;
    }

    /**
     * @param bufferedReader {@link BufferedReader}
     * @return {@link List}&lt;{@link List}&lt;{@link Object}&gt;&gt;
     * @throws IOException IOException
     */
    @Override
    public final @NotNull List<List<Object>> handle(@NotNull BufferedReader bufferedReader) throws IOException {
        StringBuilder textFromFile = new StringBuilder();
        List<List<Object>> res = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            textFromFile.append(line);
        }

        JsonElement jsonObject = JsonParser.parseString(textFromFile.toString());
        JsonArray array = jsonObject.getAsJsonArray();
        array.iterator().forEachRemaining((object) -> {
            JsonObject object2 = object.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = object2.entrySet();
            List<Object> arrayList = new ArrayList<>();

            for(Map.Entry<String, JsonElement> entry: entries) {
                arrayList.add(entry.getValue());
            }
            res.add(arrayList);
        });

        return res;
    }

    /**
     * @param bufferedReader {@link BufferedReader}
     * @return {@link JsonArray}
     * @throws IOException IOException
     */
    public @NotNull JsonArray fromJSON(@NotNull BufferedReader bufferedReader) throws IOException {
        //TODO: Alter logic in order to divide json text in another class
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

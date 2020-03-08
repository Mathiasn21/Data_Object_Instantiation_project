package framework.utilities.data.handle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class JSONHandler implements IHandle{
    private Class<?>[] primaryKeyTypes;
    private String[] primaryKeys;


    @Override
    public void setPrimaryKeyTypes(Class<?>[] types) {
        primaryKeyTypes = types;
    }

    @Override
    public void setPrimaryKeys(String[] keys) {
        primaryKeys = keys;
    }

    @Override
    public List<List<Object>> handle(BufferedReader bufferedReader) throws IOException {
        StringBuilder textFromFile = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            textFromFile.append(line);
        }

        JsonElement jsonObject = JsonParser.parseString(textFromFile.toString());
        JsonArray jsonStr = jsonObject.getAsJsonArray();
        return null;
    }
}

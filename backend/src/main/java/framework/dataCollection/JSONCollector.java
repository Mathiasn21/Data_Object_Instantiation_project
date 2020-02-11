package framework.dataCollection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import framework.HandleStorage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JSONCollector extends Collector {
    private final String filename;
    private final static Map<Setting, String> settings = new HashMap<>();
    private List<String> primaryKeys;
    private List<JsonObject> jsonObjectValues;

    public JSONCollector(@NotNull String filename, String ...primaryKeys) {
        this.filename = filename;
        this.primaryKeys = Arrays.asList(primaryKeys);
    }
    public JSONCollector(@NotNull File file, String ...primaryKeys) {
        this(file.getName(), primaryKeys);
    }

    public JSONCollector(@NotNull String filename) {
        this(filename, (String) null);
    }

    public JSONCollector(@NotNull File file) {
        this(file.getName());
    }

    @Override
    public String[][] getAllColumns() {
        int columnLength = jsonObjectValues.size();
        String[] primaryColumns = getAllPrimaryColumns();
        int rowLength = primaryColumns.length;
        String[][] rows = new String[columnLength][rowLength];

        for(int i = 0; i < columnLength; i++){
            JsonObject jsonObject = jsonObjectValues.get(i);
            for(int j = 0; j < rowLength; j++){
                String column = primaryColumns[j];
                rows[i][j] = String.valueOf(jsonObject.get(column));
            }

        }
        return new String[0][];
    }

    private boolean notFound = false;
    @Override
    public void loadAndReadFile() throws IOException {
        JsonArray jsonStr = HandleStorage.readFromJSONFile(filename);
        jsonObjectValues = new ArrayList<>();
        jsonStr.iterator().forEachRemaining((element) -> {
            if(primaryKeys.size() == 1 && notFound){
                 setPrimaryColumns(getPrimaryColumnsWithKey(primaryKeys.get(0), element.getAsJsonObject()));
                 notFound = false;
            }else if(primaryKeys.size() == 0) {

            }else{
                jsonObjectValues.add(element.getAsJsonObject());
            }
        });
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    private List<String> getPrimaryColumnsWithKey(String primaryKey, JsonObject jsonObject) {
        List<String> res = new ArrayList<>();
        if(jsonObject.has(primaryKey)){
            res.add(jsonObject.get(primaryKey).toString());
        }
        return res;
    }

    @Override
    public Item[] getCategoryBy(String name) {
        return new Item[0];
    }
}
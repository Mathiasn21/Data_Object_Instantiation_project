package framework.dataCollection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import framework.HandleStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONCollector extends Collector {
    private final String filename;
    private final static Map<Setting, String> settings = new HashMap<>();
    private String[] jsonKeys;
    private JsonObject jsonObject;

    public JSONCollector(@NotNull String filename) {
        this.filename = filename;
    }
///
    public JSONCollector(@NotNull File file) {
        this(file.getName());
    }

    @Override
    public String[][] getAllColumns() {
        return new String[0][];
    }

    @Override
    public void loadAndReadFile() throws IOException {
        JsonArray jsonStr = HandleStorage.readFromJSONFile(filename);
        jsonStr.iterator().forEachRemaining((element) -> {

            System.out.println(element.getAsJsonObject());
        });





        //TODO: Get primary columns
        //TODO: Get informational columns
    }

    @Override
    public Item[] getCategoryBy(String name) {
        return new Item[0];
    }
}
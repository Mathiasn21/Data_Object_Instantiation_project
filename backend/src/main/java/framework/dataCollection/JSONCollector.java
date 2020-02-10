package framework.dataCollection;

import com.google.gson.JsonArray;
import com.google.gson.internal.bind.util.ISO8601Utils;
import framework.HandleStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JSONCollector extends Collector {
    private final String filename;
    private final static Map<Setting, String> settings = new HashMap<>();
    private List<String[]> informationalRows = new ArrayList<>();
    private List<String[]> rows;

    public JSONCollector(@NotNull String filename) {
        this.filename = filename;
    }
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
        jsonStr.iterator().forEachRemaining((element) -> System.out.println(element.toString()));
        //TODO: Get 
    }

    @Override
    public Item[] getCategoryBy(String name) {
        return new Item[0];
    }
}
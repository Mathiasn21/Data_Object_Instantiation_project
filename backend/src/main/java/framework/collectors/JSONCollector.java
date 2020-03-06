package framework.collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import framework.annotations.DataObject;
import framework.utilities.data.retrieve.ReadFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;


/** Class for collecting data. Extends {@link Collector}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class JSONCollector extends Collector implements IJSON {
    private String filename;
    private final static Map<Setting, String> settings = new HashMap<>();
    private List<DataObject> primaryKeys;
    private List<JsonObject> jsonObjectValues;

    /**
     * @param filename String
     */
    public JSONCollector(@NotNull String filename) {
        this.filename = filename;
        setPrimaryColumns(primaryKeys);
    }

    /**
     * Default constructor with a no file related to it.
     * Filename must be set if one wishes to further utilize this class
     */
    public JSONCollector(){
        filename = null;
    }

    /**
     * @param file {@link File}
     */
    public JSONCollector(@NotNull File file) {
        this(file.getName());
    }

    /**
     * @throws IOException IOException
     */
    @Override
    public void loadAndReadFile() throws IOException {
        JsonArray jsonStr = ReadFile.fromJSON(filename);
        jsonObjectValues = new ArrayList<>();
        jsonStr.iterator().forEachRemaining((element) -> {
            jsonObjectValues.add(element.getAsJsonObject());
        });
    }

    @Override
    public String toString() {
        return "JSON Collector";
    }
}

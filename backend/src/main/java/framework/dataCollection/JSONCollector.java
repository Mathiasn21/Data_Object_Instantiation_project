package framework.dataCollection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import framework.HandleStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;


/** Class for collecting data. Implements {@link ICollector}
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class JSONCollector extends Collector {
    private final String filename;
    private final static Map<Setting, String> settings = new HashMap<>();
    private List<String> primaryKeys;
    private List<JsonObject> jsonObjectValues;

    /**
     * @param filename String
     * @param primaryKeys ...String
     */
    public JSONCollector(@NotNull String filename, String ...primaryKeys) {
        this.filename = filename;
        this.primaryKeys = Arrays.asList(primaryKeys);
        setPrimaryColumns(primaryKeys);
    }

    /**
     * @param file {@link File}
     * @param primaryKeys ...String
     */
    public JSONCollector(@NotNull File file, String ...primaryKeys) {
        this(file.getName(), primaryKeys);
    }


    /**
     * @return String[][]
     */
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
        return rows;
    }

    /**
     * @throws IOException IOException
     */
    @Override
    public void loadAndReadFile() throws IOException {
        JsonArray jsonStr = HandleStorage.readFromJSONFile(filename);
        jsonObjectValues = new ArrayList<>();
        jsonStr.iterator().forEachRemaining((element) -> {
            jsonObjectValues.add(element.getAsJsonObject());
        });
    }

    /**
     * @param name String
     * @return {@link Item[]}
     */
    @Override
    public Item[] getCategoryBy(String name) {
        return new Item[0];
    }

    /**
     * @param columnName String
     * @return String[]
     */
    @Override
    public String[] getColumnBy(String columnName) {
        return new String[0];
    }
}
package doiframework.utilities.handlers;

import com.google.gson.*;
import doiframework.utilities.Parser;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static org.apache.commons.lang3.math.NumberUtils.*;

/** Class that contains concrete instructions for handling JSON resource.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.4.0
 */
public class JSONHandler implements IHandle{
    private Class<?>[] primaryKeyTypes;

    /**
     * @param bufferedReader {@link BufferedReader}
     * @return {@link List}&lt;{@link List}&lt;{@link Object}&gt;&gt;
     * @throws IOException IOException
     */
    @Override
    public @NotNull List<Object[]> handle(@NotNull BufferedReader bufferedReader) throws IOException {
        List<Object[]> res = new ArrayList<>();
        StringBuilder textFromFile = getJSONStringFrom(bufferedReader);
        JsonElement jsonObject = JsonParser.parseString(textFromFile.toString());

        if(jsonObject.isJsonArray()){
            JsonArray array = jsonObject.getAsJsonArray();
            array.iterator().forEachRemaining((object) -> addObjectsToArray(res, object));
        }else{ addObjectsToArray(res, jsonObject); }
        return res;
    }

    private void addObjectsToArray(@NotNull List<Object[]> res, @NotNull JsonElement object) {
        JsonObject object2  = object.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = object2.entrySet();
        List<Object> arrayList = new ArrayList<>();

        for(Map.Entry<String, JsonElement> entry: entries) {
            JsonElement element = entry.getValue();
            if(element.isJsonPrimitive()){
                String value = entry.getValue().getAsString();

                if(isCreatable(value)){
                    Number number = createNumber(value);
                    arrayList.add(number);
                    continue;
                }
                arrayList.add(value);

            }else if(element.isJsonArray()){
                Object arr = parseToPrimitiveArray(element.getAsJsonArray());
                arrayList.add(arr);
            }
        }
        res.add(arrayList.toArray());
    }

    private Object parseToPrimitiveArray(@NotNull JsonArray array){
        Class<?> primitiveType;
        JsonArray arr = array;
        while(true){
            JsonElement element = arr.get(0);
            if(!element.isJsonArray()){
                primitiveType = Parser.PrimitiveWrapperToPrimitiveType(findPrimitiveTypeFrom(arr.get(0), arr.get(arr.size() - 1)));
                break;
            }
            arr = (JsonArray) element;
        }
        return buildArray(array, primitiveType);
    }

    private Object buildArray(@NotNull JsonArray array, Class<?> primitiveType) {
        Object res = null;

        int outerSize = array.size();
        for (int i = 0; i < outerSize; i++) {
            JsonElement element = array.get(i);

            if (!element.isJsonArray()) {
                int size = array.size();
                Object arr = Array.newInstance(primitiveType, size);

                for (int j = 0; j < size; j++) {
                    Array.set(arr, j, Parser.toPrimitiveValueGivenType(primitiveType, array.get(j).toString()));
                }
                return arr;
            } else {
                Object arr = buildArray(((JsonArray) array.get(i)), primitiveType);
                if(res == null){
                    res = Array.newInstance(arr.getClass(), outerSize);
                }
                Array.set(res, i, arr);
            }
        }
        return res;
    }


    private Class<?> findPrimitiveTypeFrom(@NotNull JsonElement firstElement, @NotNull JsonElement secondElement) {
        if(!(isCreatable(firstElement.toString()) && isCreatable(secondElement.toString()))){
            throw new Error();//TODO: alter to a more descriptive error
        }
        Number first = createNumber(firstElement.toString());
        Number second = createNumber(firstElement.toString());
        if(second.getClass() != second.getClass()){
            throw new Error();
        }
        return first.getClass();
    }


    @NotNull
    private StringBuilder getJSONStringFrom(@NotNull BufferedReader bufferedReader) throws IOException {
        StringBuilder textFromFile = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) { textFromFile.append(line); }
        return textFromFile;
    }

    /**
     * This method retrieves a list, execute a type Class and a json string.
     * @param type             T[]
     * @param jsonTextFromFile String
     * @param <T>              T
     * @return {@link List}&lt;{@link T}&gt;
     */
    @NotNull
    @SuppressWarnings("unchecked")//Will always be possible unless it's a null value
    public final <T> List<T> listFromJson(@NotNull Class<T> type, @NotNull String jsonTextFromFile) {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        Class<T[]> arrClass = (Class<T[]>) Array.newInstance(type, 0).getClass();
        T[] arrangementArray = gson.fromJson(jsonTextFromFile, arrClass);
        return (Arrays.asList(arrangementArray));
    }

    /**
     * Utilizes parametrization combined with generics, in order to
     * convert a execute T[] object and its specified Class template to json format.
     * @param list T[]
     * @return String
     */
    private String toJson(@NotNull List<String> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list);
    }
}

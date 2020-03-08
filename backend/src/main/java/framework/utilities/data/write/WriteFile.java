package framework.utilities.data.write;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;

//TODO: implement remaining logic given another project code -> Mathias
/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class WriteFile implements IWriteFile{
    WriteFile() {
    }

    /**
     * Utilizes parametrization combined with generics, in order to
     * convert a given T[] object and its specified Class template to json format.
     * @param list T[]
     * @return String
     */
    private String toJson(List<String> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list);
    }

    /**
     * @param resource {@link File}
     * @param data String
     * @throws IOException IOException
     */
    @Override
    public final void given(@NotNull File resource, @NotNull String data) throws IOException {
        String filepath = "/files/" + resource;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("").getAbsolutePath() + filepath))) {
            bufferedWriter.write(data);
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    /**
     * Standard method for writing data to a given file.
     * This method does not append but overwrites!
     * Utilizes a relative path for top level directory, plus filename.extension
     * @param resource   String
     * @param data String
     */
    @Contract(pure = true)
    @Override
    public final void given(@NotNull String resource, @NotNull String data) throws IOException {

    }
}

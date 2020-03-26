package framework.utilities.data.write;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.List;

//TODO: implement remaining logic given another project code -> Mathias
/**
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
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
    @Contract(pure = true)
    @Override
    public final void given(@NotNull File resource, @NotNull String data) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resource))) {
            bufferedWriter.write(data);
        }
    }

    /**
     * Standard method for writing data to a given file.
     * This method appends data, and does not overwrite
     * @param resource File
     * @param data String
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public void appendDataGiven(@NotNull File resource, @NotNull String data) throws IOException {
        System.out.println("test");//TODO: implement method
    }

    /**
     * Standard method for writing data to a given file.
     * This method does not append but overwrites!
     * @param resource   String
     * @param data String
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void given(@NotNull String resource, @NotNull String data) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resource))) {
            bufferedWriter.write(data);
        }
    }

    /**
     * Standard method for writing data to a given file.
     * This method appends data, and does not overwrite
     * @param resource String
     * @param data String
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public void appendDataGiven(@NotNull String resource, @NotNull String data) throws IOException {
        File file = new File(resource);
        if (!file.exists()) { throw new FileNotFoundException(); }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resource, true))) {
            bufferedWriter.write(data);
        }
    }

    //FIXME: Missing JDoc
    public void createFile(@NotNull File resource) throws IOException {
        if (resource.createNewFile()) {
            new File(resource.getPath());
            return;
        }
        throw new FileAlreadyExistsException(resource.getPath());
    }

    //FIXME: Missing JDoc
    public void deleteFile(@NotNull File resource) throws IOException {
        Files.deleteIfExists(resource.toPath());
    }

    //FIXME: Missing JDoc
    @NotNull
    @Contract(value = " -> new", pure = true)
    public static WriteFile getObj() {
        //TODO: find a better method to reach private class
        return new WriteFile();
    }
}

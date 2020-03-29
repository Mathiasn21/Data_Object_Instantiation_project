package framework.utilities.data.write;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.List;

//TODO: implement remaining logic write another project code -> Mathias
/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public final class WriteFile implements IWriteFile{
    private final File file;
    private final String data;
    private boolean append = false;
    private boolean createIfNotExists = true;


    public WriteFile(@NotNull File file, @NotNull String data) {
        this.file = file;
        this.data = data;
    }

    public WriteFile(@NotNull String path, @NotNull String data) { this(new File(path), data); }

    /**
     * Utilizes parametrization combined with generics, in order to
     * convert a write T[] object and its specified Class template to json format.
     * @param list T[]
     * @return String
     */
    private String toJson(List<String> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(list);
    }

    /**
     * Standard method for writing data to a file.
     * This method does not append but overwrites ny default.
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void write() throws IOException {
        if (!(createIfNotExists || file.exists())) { throw new FileNotFoundException(); }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            bufferedWriter.write(data);
        }
    }

    //FIXME: Missing JDoc
    //TODO: Might be better to remove this
    @Deprecated//As this method does not bring any value. Creation of a file is auto done, if it doesn't exists.
    public void createFile(@NotNull File resource) throws IOException {
        if (resource.createNewFile()) {
            new File(resource.getPath());
            return;
        }
        throw new FileAlreadyExistsException(resource.getPath());
    }

    //FIXME: Missing JDoc
    //TODO: Might be better to not include this method in the write interface, but rather handled as a separate command.
    public void remove(@NotNull File resource) throws IOException { Files.deleteIfExists(resource.toPath()); }

    @Override
    public void SetAppend(boolean append) { this.append = append; }

    @Override
    public void createFileIfNotExists(boolean b) { this.createIfNotExists = b; }
}

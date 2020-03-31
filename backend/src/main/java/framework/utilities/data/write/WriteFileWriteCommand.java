package framework.utilities.data.write;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public final class WriteFileWriteCommand implements IWriteFileWriteCommand {
    private final File file;
    private final String data;
    private boolean append = false, createIfNotExists = true;

    public WriteFileWriteCommand(@NotNull File file, @NotNull String data) {
        this.file = file;
        this.data = data;
    }

    public WriteFileWriteCommand(@NotNull String path, @NotNull String data) { this(new File(path), data); }

    /**
     * Utilizes parametrization combined with generics, in order to
     * convert a execute T[] object and its specified Class template to json format.
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
    public final void execute() throws IOException {
        if (createIfNotExists && !file.exists()) { throw new FileNotFoundException(); }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            bufferedWriter.write(data);
        }
    }

    //FIXME: Missing JDoc
    //TODO: Might be better to not include this method in the execute interface, but rather handled as a separate command.
    @Deprecated
    public void remove(@NotNull File resource) throws IOException { Files.deleteIfExists(resource.toPath()); }

    @Override
    public void SetAppending(boolean append) { this.append = append; }

    @Override
    public void createFileIfNotExists(boolean b) { this.createIfNotExists = b; }
}

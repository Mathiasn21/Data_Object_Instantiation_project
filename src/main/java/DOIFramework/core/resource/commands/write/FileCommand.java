package DOIFramework.core.resource.commands.write;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0.0
 */
public final class FileCommand implements IWriteFileCommand {
    private final File file;
    private boolean append = false, createIfNotExists = true;

    public FileCommand(@NotNull File file) { this.file = file; }

    public FileCommand(@NotNull String path) { this(new File(path)); }

    /**
     * Standard method for writing resource to a file.
     * This method does not append but overwrites ny default.
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void execute(@NotNull String data) throws IOException {
        if (createIfNotExists && !file.exists()) { throw new FileNotFoundException(); }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            bufferedWriter.write(data);
        }
    }

    @Override
    public void SetAppending(boolean append) { this.append = append; }

    @Override
    public void createFileIfNotExists(boolean b) { this.createIfNotExists = b; }
}

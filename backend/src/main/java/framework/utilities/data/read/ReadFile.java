package framework.utilities.data.read;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class ReadFile implements IReadFile{
    private String path;
    /**
     * Returns a buffer for reading the given file.
     * Charset is standard UTF-8
     * @param fileName String
     * @return BufferedReader BufferedReader {@link BufferedReader}
     * @throws IOException IOException {@link IOException}
     */
    public void given(@NotNull String fileName) throws IOException {
        path = fileName;
    }


    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
     */
    @NotNull
    @Contract(" -> new")
    @Override
    public BufferedReader read() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
    }

    /**
     * @param resource String
     * @return {@link ReadFile}
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public void given(@NotNull File resource) throws IOException {
        path = resource.getPath();
    }
}

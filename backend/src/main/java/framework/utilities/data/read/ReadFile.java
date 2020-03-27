package framework.utilities.data.read;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.0.0
 */
public final class ReadFile implements IRead {
    private final String path;

    public ReadFile(@NotNull String path) { this.path = path; }
    public ReadFile(@NotNull File path) { this.path = path.getPath(); }

    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
     */
    @NotNull
    @Contract(" -> new")
    @Override
    public final BufferedReader read() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
    }

    @Override
    public final String getSourceName() { return path; }
}

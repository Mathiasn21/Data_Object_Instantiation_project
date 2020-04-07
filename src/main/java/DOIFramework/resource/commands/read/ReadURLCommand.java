package DOIFramework.resource.commands.read;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 2.0.0
 */
public final class ReadURLCommand implements IReadURLCommand {
    private final String name;
    private final URL url;

    public ReadURLCommand(@NotNull String url) throws MalformedURLException { this(new URL(url)); }

    @Contract(pure = true)
    public ReadURLCommand(@NotNull URL url) {
        this.url = url;
        this.name = url.getPath();
    }

    /**
     * @return {@link BufferedReader}
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final @NotNull BufferedReader execute() throws IOException {
        return new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
    }

    @Override
    public final String getSourceName() { return name; }
}

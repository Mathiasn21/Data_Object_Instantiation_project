package framework.utilities.data.read;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class ReadURL implements IReadURL{
    private String name;

    private String url;

    /**
     * @param resource URL
     */
    @Contract(pure = true)
    @Override
    public void given(@NotNull URL resource) {
        url = resource.toString();
    }

    /**
     * @param resource String
     */
    @Contract(pure = true)
    @Override
    public void given(@NotNull String resource) {
        url = resource;
    }

    /**
     * @return {@link BufferedReader}
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public @NotNull BufferedReader read() throws IOException {
        return new BufferedReader(new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8));
    }

    @Override
    public String getSourceName() {
        return name;
    }
}

package framework.utilities.data.read;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class ReadURL implements IReadURL{
    private String name;

    private String url;

    /**
     * @param resource String
     * @return {@link BufferedReader}
     * @throws MalformedURLException MalformedURLException
     */
    @Contract(pure = true)
    @Override
    public void given(@NotNull URL resource) throws IOException {
        url = resource.toString(); //her må det parses til String
    }

    /**
     * @param resource String
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public void given(@NotNull String resource) throws IOException {
        url = resource;
    }

    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
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

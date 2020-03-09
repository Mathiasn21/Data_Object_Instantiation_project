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
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
final class ReadURL implements IReadURL{

    private String url;

    /**
     * @param resource String
     * @return {@link BufferedReader}
     * @throws MalformedURLException MalformedURLException
     */
    @Contract(pure = true)
    @Override
    public @NotNull BufferedReader given(@NotNull URL resource) throws IOException {
        return new BufferedReader(new InputStreamReader(resource.openStream()));
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
}

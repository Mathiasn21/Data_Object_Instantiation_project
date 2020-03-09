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
final class ReadURL implements IReadURL{

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
        /*//ER DETTE BEDRE HMM
        URL oracle = new URL(url);
        URLConnection ucon = oracle.openConnection();
        return new BufferedReader(new InputStreamReader(ucon.getInputStream(), StandardCharsets.UTF_8));*/

        return new BufferedReader(new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8));
    }
}

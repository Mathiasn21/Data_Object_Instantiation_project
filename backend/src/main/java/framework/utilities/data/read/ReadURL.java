package framework.utilities.data.read;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
final class ReadURL implements IReadURL{

    /**
     * @param resource String
     * @return {@link BufferedReader}
     * @throws MalformedURLException MalformedURLException
     */
    @Contract(pure = true)
    @Override
    public @NotNull BufferedReader given(@NotNull URL resource) throws MalformedURLException {
        //TODO: Implement logic
        return null;
    }

    /**
     * @param resource String
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public void given(@NotNull String resource) throws IOException {
        //TODO: Implement logic
    }

    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
     */
    @Contract(pure = true)
    @Override
    public @NotNull BufferedReader read() throws FileNotFoundException {
        //TODO: Implement logic
        return null;
    }
}

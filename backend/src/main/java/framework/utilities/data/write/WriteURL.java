package framework.utilities.data.write;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class WriteURL implements IWriteURL {
    WriteURL() {
    }

    /**
     * @param resource String
     * @param data String
     * @throws MalformedURLException MalformedURLException
     */
    @Override
    public final void given(@NotNull URL resource, @NotNull String data) throws MalformedURLException {

    }

    /**
     * @param resource String
     * @param data     String
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void given(@NotNull String resource, @NotNull String data) throws IOException {

    }
}

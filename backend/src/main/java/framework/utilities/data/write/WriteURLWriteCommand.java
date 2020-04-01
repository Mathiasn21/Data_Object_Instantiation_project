package framework.utilities.data.write;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Primary function of this class is to, POST data to a
 * URL.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21 - Architecture - Refactoring
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0.0
 */
public final class WriteURLWriteCommand implements IWriteURLWriteCommand {
    private final URL url;
    private final String data;

    public WriteURLWriteCommand(@NotNull URL url, @NotNull String data) {
        this.url = url;
        this.data = data;
    }

    public WriteURLWriteCommand(@NotNull String url, @NotNull String data) throws MalformedURLException {
        this(new URL(url), data);
    }

    /**
     * Standard method for writing data to a execute URL.
     * This method does not append but overwrites.
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void execute() throws IOException {
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        try (Writer w = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            w.write(data);
        }
    }
}

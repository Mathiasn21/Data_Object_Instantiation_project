package framework.utilities.data.write;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Primary function of this class is to, POST data to a
 * URL.
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class WriteURL implements IWriteURL {
    private final URL url;
    private final String data;

    public WriteURL(@NotNull URL url, @NotNull String data) {
        this.url = url;
        this.data = data;
    }

    public WriteURL(@NotNull String url, @NotNull String data) throws MalformedURLException {
        this(new URL(url), data);
    }

    /**
     * Standard method for writing data to a write URL.
     * This method does not append but overwrites.
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void write() throws IOException {
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        try (Writer w = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            w.write(data);
        }
    }
}

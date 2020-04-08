package DOIFramework.core.resource.commands.write;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Primary function of this class is to, POST resource to a URL.
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
**/
public final class URLCommand implements IWriteURLCommand {
    private final URL url;

    public URLCommand(@NotNull URL url) { this.url = url; }

    public URLCommand(@NotNull String url) throws MalformedURLException { this(new URL(url)); }

    /**
     * Standard method for writing resource to a execute URL.
     * This method does not append but overwrites.
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void execute(@NotNull String data) throws IOException {
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        try (Writer w = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            w.write(data);
        }
    }
}

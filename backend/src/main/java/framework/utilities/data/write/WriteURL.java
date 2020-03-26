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
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @version 1.0
 */
public final class WriteURL implements IWriteURL {

    WriteURL() {
    }

    /**
     * Standard method for wriing data to a given URL.
     * This method does not append but overwrites.
     * @param resource String
     * @param data String
     * @throws IOException IOException
     */
    @Override
    public final void given(@NotNull URL resource, @NotNull String data) throws IOException {
        URLConnection conn = resource.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();

        try (Writer w = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            w.write(data);
        }
    }


    /**
     * Standard method for writing data to a given URL.
     * This method does not append but overwrites.
     * @param resource URL
     * @param data String
     * @throws IOException IOException
     */
    @Override
    public void appendDataGiven(@NotNull URL resource, @NotNull String data) throws IOException {
        //TODO: test this, i think this is the wrong way to append
        URLConnection conn = resource.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();

        try (Writer w = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            w.append(data);
        }
    }

    /**
     * Standard method for writing data to a given URL.
     * This method does not append but overwrites.
     * @param resource String
     * @param data String
     * @throws IOException IOException
     */
    @Contract(pure = true)
    @Override
    public final void given(@NotNull String resource, @NotNull String data) throws IOException {
        URL url1 = new URL(resource);
        URLConnection conn = url1.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();

        try (Writer w = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            w.write(data);
        }

    }

    /**
     * Standard method for writing data to a given URL.
     * This method does not append but overwrites.
     * @param resource String
     * @param data String
     * @throws IOException IOException
     */
    @Override
    public void appendDataGiven(@NotNull String resource, @NotNull String data) throws IOException {
        //TODO: test this, i think this is the wrong way to append
        URL url = new URL(resource);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();

        try (Writer w = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            w.append(data);
        }
    }
    public static WriteURL getObj()
    {
        //TODO: find a better method to reach private class
        return new WriteURL();
    }
}

package framework.utilities.data;

import framework.utilities.data.read.IRead;
import framework.utilities.data.read.IReadURL;
import framework.utilities.data.read.ReadFile;
import framework.utilities.data.read.ReadURL;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Builds a resource from which to fetch data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public class ResourceBuilder {
    private IRead read;

    /**
     * Leave be, prevents unwanted instantiation.
     */
    @Contract(pure = true)
    ResourceBuilder() {
    }

    /**
     * @throws IOException e
     * @param file String
     * @return {@link ResourceBuilder}
     */
    public ResourceBuilder fromFile(@NotNull String file) throws IOException {
        ReadFile readFile = new ReadFile();
        readFile.given(file);
        read = readFile;
        return this;
    }

    /**
     * @throws IOException e
     * @param file {@link File}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromFile(@NotNull File file) throws IOException {
        ReadFile readFile = new ReadFile();
        readFile.given(file);
        read = readFile;
        return this;
    }

    /**
     * @throws IOException e
     * @param url String
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURL(@NotNull String url) throws IOException {
        IReadURL readURL = new ReadURL();
        readURL.given(url);
        read = readURL;
        return this;
    }

    /**
     * @throws IOException e
     * @param url {@link URL}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURL(@NotNull URL url) throws IOException {
        IReadURL readURL = new ReadURL();
        readURL.given(url);
        read = readURL;
        return this;
    }

    /**
     * @return {@link Resource}
     */
    @NotNull
    @Contract(value = " -> new", pure = true)
    public Resource build() {
        return new Resource(read);
    }
}

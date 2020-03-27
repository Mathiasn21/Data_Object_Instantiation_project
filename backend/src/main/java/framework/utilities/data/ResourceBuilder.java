package framework.utilities.data;

import framework.utilities.data.read.IRead;
import framework.utilities.data.read.ReadFile;
import framework.utilities.data.read.ReadURL;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Builds a resource from which to fetch data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public final class ResourceBuilder {
    private final List<IRead> readers = new ArrayList<>();

    /**
     * Leave be, prevents unwanted instantiation.
     */
    @Contract(pure = true)
    ResourceBuilder() {

    }

    /**
     * @throws IOException e
     * @param path String
     * @return {@link ResourceBuilder}
     */
    public ResourceBuilder fromFile(@NotNull String path) throws IOException {
        ReadFile readFile = new ReadFile(path);
        readers.add(readFile);
        return this;
    }

    /**
     * @throws IOException e
     * @param file {@link File}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromFile(@NotNull File file) throws IOException {
        ReadFile readFile = new ReadFile(file);
        readers.add(readFile);
        return this;
    }

    /**
     * @throws IOException e
     * @param paths String...
     * @return {@link ResourceBuilder}
     */
    public ResourceBuilder fromFile(@NotNull String... paths) throws IOException {
        for (String path : paths) {
            IRead readFile = new ReadFile(path);
            readers.add(readFile);
        }
        return this;
    }

    /**
     * @throws IOException e
     * @param files {@link File...}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromFile(@NotNull File... files) throws IOException {
        for (File file : files) {
            IRead readFile = new ReadFile(file);
            readers.add(readFile);
        }
        return this;
    }

    /**
     * @throws IOException e
     * @param url String
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURL(@NotNull String url) throws IOException {
        IRead readURL = new ReadURL(url);
        readers.add(readURL);
        return this;
    }

    /**
     * @throws IOException e
     * @param url {@link URL}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURL(@NotNull URL url) throws IOException {
        IRead readURL = new ReadURL(url);
        readers.add(readURL);
        return this;
    }

    /**
     * @throws IOException e
     * @param urls String...
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURLs(@NotNull String... urls) throws IOException {
        for (String url : urls) {
            IRead readURL = new ReadURL(url);
            readers.add(readURL);
        }
        return this;
    }

    /**
     * @throws IOException e
     * @param urls {@link URL...}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURLs(@NotNull URL... urls) throws IOException {
        for (URL url : urls) {
            IRead readURL = new ReadURL(url);
            readers.add(readURL);
        }
        return this;
    }




    /**
     * @return {@link Resource}
     */
    @NotNull
    @Contract(value = " -> new", pure = true)
    public Resource build() { return new Resource(readers.get(0)); }

    /**
     * @return {@link List}&lt;{@link Resource}&gt;
     */
    @NotNull
    public List<Resource> buildAll() {
        List<Resource> resources = new ArrayList<>(readers.size());
        for (IRead reader : readers) {
            resources.add(new Resource(reader));
        }
        return resources;
    }
}

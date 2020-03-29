package framework.utilities.data;

import framework.utilities.data.read.IReadCommand;
import framework.utilities.data.read.ReadFileCommand;
import framework.utilities.data.read.ReadURLCommand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds a resource from which to fetch data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public final class ResourceBuilder {
    private final List<IReadCommand> readers = new ArrayList<>();

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
        ReadFileCommand readFileCommand = new ReadFileCommand(path);
        readers.add(readFileCommand);
        return this;
    }

    /**
     * @throws IOException e
     * @param file {@link File}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromFile(@NotNull File file) throws IOException {
        ReadFileCommand readFileCommand = new ReadFileCommand(file);
        readers.add(readFileCommand);
        return this;
    }

    /**
     * @throws IOException e
     * @param paths String...
     * @return {@link ResourceBuilder}
     */
    public ResourceBuilder fromFile(@NotNull String... paths) throws IOException {
        for (String path : paths) {
            IReadCommand readFile = new ReadFileCommand(path);
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
            IReadCommand readFile = new ReadFileCommand(file);
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
        IReadCommand readURL = new ReadURLCommand(url);
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
        IReadCommand readURL = new ReadURLCommand(url);
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
            IReadCommand readURL = new ReadURLCommand(url);
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
            IReadCommand readURL = new ReadURLCommand(url);
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
        for (IReadCommand reader : readers) {
            resources.add(new Resource(reader));
        }
        return resources;
    }
}

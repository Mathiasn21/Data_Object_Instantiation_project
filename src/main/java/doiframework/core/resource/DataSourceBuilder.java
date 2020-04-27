package doiframework.core.resource;

import doiframework.core.resource.commands.read.IReadCommand;
import doiframework.core.resource.commands.read.ReadToFile;
import doiframework.core.resource.commands.read.ReadToURL;
import doiframework.core.resource.commands.write.IWriteCommand;
import doiframework.core.resource.commands.write.WriteToFile;
import doiframework.core.resource.commands.write.WriteToURL;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds a resource from which to fetch resource
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.6.0
 */
public final class DataSourceBuilder {
    private final List<IReadCommand> readers = new ArrayList<>();
    private final List<IWriteCommand> writers = new ArrayList<>();

    /**
     * Leave be, prevents unwanted instantiation.
     */
    @Contract(pure = true)
    DataSourceBuilder() {
    }

    /**
     * @param path String
     * @return {@link DataSourceBuilder}
     */
    public DataSourceBuilder fromFile(@NotNull String path) {
        ReadToFile readToFile = new ReadToFile(path);
        readers.add(readToFile);
        return this;
    }

    /**
     * @param file {@link File}
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder fromFile(@NotNull File file) {
        ReadToFile readToFile = new ReadToFile(file);
        readers.add(readToFile);
        return this;
    }

    /**
     * @param paths String...
     * @return {@link DataSourceBuilder}
     */
    public DataSourceBuilder fromFile(@NotNull String... paths) {
        for (String path : paths) {
            IReadCommand readFile = new ReadToFile(path);
            readers.add(readFile);
        }
        return this;
    }

    /**
     * @param files {@link File}...
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder fromFile(@NotNull File... files) {
        for (File file : files) {
            IReadCommand readFile = new ReadToFile(file);
            readers.add(readFile);
        }
        return this;
    }

    /**
     * @throws IOException e
     * @param url String
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder fromURL(@NotNull String url) throws IOException {
        IReadCommand readURL = new ReadToURL(url);
        readers.add(readURL);
        return this;
    }

    /**
     * @param url {@link URL}
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder fromURL(@NotNull URL url) {
        IReadCommand readURL = new ReadToURL(url);
        readers.add(readURL);
        return this;
    }

    /**
     * @throws IOException e
     * @param urls String...
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder fromURLs(@NotNull String... urls) throws IOException {
        for (String url : urls) {
            IReadCommand readURL = new ReadToURL(url);
            readers.add(readURL);
        }
        return this;
    }

    /**
     * @param urls {@link URL}...
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder fromURLs(@NotNull URL... urls) {
        for (URL url : urls) {
            IReadCommand readURL = new ReadToURL(url);
            readers.add(readURL);
        }
        return this;
    }


    /**
     * @param file {@link File}
     * @return {@link DataSourceBuilder}
     */
    public DataSourceBuilder toFile(@NotNull File file) {
        IWriteCommand writeFile = new WriteToFile(file);
        writers.add(writeFile);
        return this;
    }

    /**
     * @param file {@link URL}
     * @return {@link DataSourceBuilder}
     */
    public DataSourceBuilder toFile(@NotNull String file) {
        IWriteCommand writeFile = new WriteToFile(file);
        writers.add(writeFile);
        return this;
    }

    /**
     * @param files String...
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder toFiles(@NotNull String ...files) {
        for (String file : files) {
            IWriteCommand writeFile = new WriteToFile(file);
            writers.add(writeFile);
        }
        return this;
    }

    /**
     * @param files String...
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder toFiles(@NotNull File ...files) {
        for (File file : files) {
            IWriteCommand writeFile = new WriteToFile(file);
            writers.add(writeFile);
        }
        return this;
    }



    /**
     * @param url {@link URL}
     * @return {@link DataSourceBuilder}
     */
    public DataSourceBuilder toURL(@NotNull URL url) {
        IWriteCommand writeURL = new WriteToURL(url);
        writers.add(writeURL);
        return this;
    }

    /**
     * @param url {@link URL}
     * @throws MalformedURLException MalformedURLException
     * @return {@link DataSourceBuilder}
     */
    public DataSourceBuilder toURL(@NotNull String url) throws MalformedURLException {
        IWriteCommand writeURL = new WriteToURL(url);
        writers.add(writeURL);
        return this;
    }

    /**
     * @throws IOException e
     * @param urls String...
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder toURLs(@NotNull String ...urls) throws IOException {
        for (String url : urls) {
            IWriteCommand writeURL = new WriteToURL(url);
            writers.add(writeURL);
        }
        return this;
    }

    /**
     * @param urls String...
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public DataSourceBuilder toURLs(@NotNull URL ...urls) {
        for (URL url : urls) {
            IWriteCommand writeURL = new WriteToURL(url);
            writers.add(writeURL);
        }
        return this;
    }

    /**
     * @return {@link DataSource}
     */
    @NotNull
    @Contract(value = " -> new", pure = true)
    public DataSource build() { return new DataSource(readers.get(0)); }

    /**
     * @return {@link List}&lt;{@link DataSource}&gt;
     */
    @NotNull
    public List<DataSource> buildAll() {
        List<DataSource> dataSources = new ArrayList<>(readers.size());
        for (IReadCommand reader : readers) {
            dataSources.add(new DataSource(reader));
        }
        return dataSources;
    }
}

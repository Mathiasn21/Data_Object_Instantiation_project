package framework.utilities.data;

import framework.utilities.data.read.IRead;
import framework.utilities.data.read.ReadFile;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public class ResourceBuilder {
    private IRead read;
    ResourceBuilder() {
    }
    //TODO: implement concrete logic for this builder




    /**
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
     * @param file {@link File}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromFile(@NotNull File file) throws IOException {
        ReadFile readFile = new ReadFile();
        readFile.given(file.getName());
        read = readFile;
        return this;
    }

    /**
     * @param url String
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURL(String url) {
        return this;
    }

    /**
     * @param url {@link URL}
     * @return {@link ResourceBuilder}
     */
    @Contract(value = "_ -> this", pure = true)
    public ResourceBuilder fromURL(URL url) {
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

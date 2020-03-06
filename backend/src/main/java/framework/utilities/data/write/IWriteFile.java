package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public interface IWriteFile extends IWrite {
    /**
     * @param resource String
     * @throws IOException IOException {@link IOException}
     */
    void given(@NotNull File resource, @NotNull String data) throws IOException;

    /**
     * @param resource String
     * @throws IOException IOException {@link IOException}
     */
    void given(@NotNull String resource, @NotNull String data) throws IOException;
}

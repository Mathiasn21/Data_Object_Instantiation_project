package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * Describes a contract for writing to a file.
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IWriteFile extends IWrite {
    /**
     * @param resource String
     * @param data String
     * @throws IOException IOException {@link IOException}
     */
    void given(@NotNull File resource, @NotNull String data) throws IOException;

    /**
     * @param resource String
     * @param data String
     * @throws IOException IOException
     * @return
     */
    void appendDataGiven(@NotNull String resource, @NotNull String data) throws IOException;

    /**
     * @param resource File
     * @param data String
     * @throws IOException IOException
     */
    void appendDataGiven(@NotNull File resource, @NotNull String data) throws IOException;
}

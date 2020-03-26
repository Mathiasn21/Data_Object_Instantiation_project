package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

/**Describes a concrete contract for writing data to a URL.
 * POST request til URL
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IWriteURL extends IWrite {

    /**
     * @param resource URL
     * @param data String
     * @throws IOException IOException
     */
    void given(@NotNull URL resource, @NotNull String data) throws IOException;

    /**
     * @param resource String
     * @param data String
     * @throws IOException IOException
     */
    void appendDataGiven(@NotNull String resource, @NotNull String data) throws IOException;

    /**
     * @param resource String
     * @param data String
     * @throws IOException IOException
     */
    void appendDataGiven(@NotNull URL resource, @NotNull String data) throws IOException;



}

package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/** Interface describing a contract for reading with a String resource
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IRead {
    /**
     * @throws IOException e
     * @param resource String
     */
    void given(@NotNull String resource) throws IOException;

    /**
     * @throws IOException e
     * @return BufferedReader {@link BufferedReader}
     */
    BufferedReader read() throws IOException;

    /**
     * @return String
     */
    String getSourceName();
}

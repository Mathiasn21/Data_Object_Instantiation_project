package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/** Interface describing a contract for reading with a String resource
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IRead {
    /**
     * @param resource String
     */
    void given(@NotNull String resource) throws IOException;

    /**
     * @return BufferedReader {@link BufferedReader}
     */
    BufferedReader read() throws IOException;
}

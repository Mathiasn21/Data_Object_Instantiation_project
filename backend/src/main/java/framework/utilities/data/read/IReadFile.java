package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/** Interface describing a contract for reading from file
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IReadFile extends IRead{
    /**
     * @param resource String
     * @throws IOException IOException {@link IOException}
     */
    void given(@NotNull File resource) throws IOException;
}

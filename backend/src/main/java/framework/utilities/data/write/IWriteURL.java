package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;

/**Describes a concrete contract for writing data to a URL
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IWriteURL extends IWrite {
    /**
     * @param resource String
     * @return BufferedReader {@link BufferedReader}
     * @throws MalformedURLException MalformedURLException {@link MalformedURLException}
     */
    void given(@NotNull URL resource, @NotNull String data) throws MalformedURLException;
}
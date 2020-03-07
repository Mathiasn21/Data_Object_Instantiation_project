package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;

/** Interface describing a contract for reading from a URL
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IReadURL extends IRead{
    /**
     * @param resource String
     * @return BufferedReader {@link BufferedReader}
     * @throws MalformedURLException MalformedURLException {@link MalformedURLException}
     */
    @NotNull
    BufferedReader given(@NotNull URL resource) throws MalformedURLException;
}

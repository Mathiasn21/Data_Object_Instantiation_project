package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Determines a contract for how to store URL POST data
 */
public interface IWriteURL extends IWrite {
    /**
     * @param resource String
     * @return BufferedReader {@link BufferedReader}
     * @throws MalformedURLException MalformedURLException {@link MalformedURLException}
     */
    void given(@NotNull URL resource, @NotNull String data) throws MalformedURLException;
}
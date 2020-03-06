package framework.utilities.data.retrieve;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
/**
 * Determines a contract for how to expose URL data
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
